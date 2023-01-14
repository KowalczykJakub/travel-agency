import com.github.javafaker.Faker;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.*;
import org.bson.Document;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final Faker faker = new Faker();
    private static final Random rd = new Random();
    private static final int NUMBER_OF_USERS = 30000;
    private static final int NUMBER_OF_PROGRAMS = 500;
    private static final int NUMBER_OF_INSTITUTIONS = 500;
    private static final int NUMBER_OF_BOOKINGS = 1500;

    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            MongoDatabase database = mongoClient.getDatabase("travel-agency");

            addUsersWithCustomersAndEmployees(database, NUMBER_OF_USERS);
            addProgram(database, NUMBER_OF_PROGRAMS);
            addInstitutions(database, NUMBER_OF_INSTITUTIONS);
            addBookings(database, NUMBER_OF_BOOKINGS);

            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUsersWithCustomersAndEmployees(MongoDatabase database, int numberOfRecords) {
        User user;
        Document document;
        Random rd = new Random();
        database.getCollection("customers").deleteMany(new Document());
        database.getCollection("users").deleteMany(new Document());
        database.getCollection("employee").deleteMany(new Document());

        for (int i = 1; i <= numberOfRecords; i++) {
            user = new User(faker);
            document = new Document();
            document.append("document_nr", user.getDocument_nr());
            document.append("email", user.getEmail());
            document.append("first_name", user.getFirst_name());
            document.append("second_name", user.getSecond_name());
            document.append("surname", user.getSurname());
            document.append("sex", user.getSex().charAt(0) + "");
            document.append("login", user.getLogin());
            document.append("password", user.getPassword());
            database.getCollection("users").insertOne(document);

            if (!(rd.nextBoolean() && rd.nextBoolean())) {

                document = new Document();
                document.append("user_id", String.valueOf(i));
                database.getCollection("customers").insertOne(document);

            } else {

                document = new Document();
                Language language;
                Delegation delegation = new Delegation();
                List<Document> languages = new ArrayList<>();

                for (int j = 0; j < rd.nextInt(4) + 1; j++) {
                    language = new Language();
                    languages.add(new Document()
                            .append("language", language.getLanguage())
                            .append("level", language.getLevel()));
                }

                document.append("department", faker.commerce().department());
                document.append("user_id", String.valueOf(i));
                document.append("languages", languages);
                document.append("delegations", new Document()
                        .append("program_id", delegation.getProgram_id())
                        .append("role", delegation.getRole())
                        .append("tour_id", delegation.getTour_id()));
                database.getCollection("employee").insertOne(document);
            }
        }
    }

    public static void addInstitutions(MongoDatabase database, int numberOfRecords) {
        database.getCollection("institutions").deleteMany(new Document());
        Document document;
        Institution institution;

        for (int i = 1; i <= numberOfRecords; i++) {

            institution = new Institution(faker);
            List<Document> photos = new ArrayList<>();

            for (int j = 0; j < rd.nextInt(10) + 1; j++) {
                photos.add(new Document().append("path_name", faker.file().fileName()));
            }

            document = new Document();
            document.append("name", institution.getName());
            document.append("type", institution.getType());
            document.append("description", institution.getDescription());
            document.append("hours", institution.getHours());
            document.append("address", institution.getAddress());
            document.append("tel", institution.getTel());
            document.append("photos", photos);
            database.getCollection("institutions").insertOne(document);
        }
    }

    public static void addBookings(MongoDatabase database, int numberOfRecords) {
        database.getCollection("bookings").deleteMany(new Document());
        Document document;
        User user;
        Booking booking = new Booking(faker);

        List<Integer> listOfCustomerIds = getCustomersIds(database);

        for (int i = 1; i <= numberOfRecords; i++) {

            List<Document> listOfParticipants = new ArrayList<>();

            for (int j = 0; j < rd.nextInt(8) + 1; j++) {
                user = new User(faker);
                listOfParticipants.add(
                        new Document()
                                .append("document_nr", user.getDocument_nr())
                                .append("first_name", user.getFirst_name())
                                .append("second_name", user.getSecond_name())
                                .append("sex", user.getSex())
                                .append("surname", user.getSurname()));
            }

            booking = new Booking(faker);
            document = new Document();
            document.append("date", booking.getDate());
            document.append("is_paid", booking.getIs_paid());
            document.append("participants_nr", booking.getParticipants_nr());
            document.append("program_id", booking.getProgram_id());
            document.append("tour_id", booking.getTour_id());
            document.append("enrollments", new Document()
                    .append("attraction_id", rd.nextInt(150))
                    .append("program_id", rd.nextInt(NUMBER_OF_PROGRAMS)));
            document.append("participants", listOfParticipants);
            document.append("payments", new Document()
                    .append("customer_id", listOfCustomerIds.get(rd.nextInt(listOfCustomerIds.size())))
                    .append("value", rd.nextInt(10000)));
            database.getCollection("bookings").insertOne(document);
        }
    }

    public static void addProgram(MongoDatabase database, int numberOfRecords) {
        database.getCollection("program").deleteMany(new Document());
        Document document;
        Program program;

        List<Document> photos = new ArrayList<>();
        List<Document> reviews = new ArrayList<>();
        List<Document> tours = new ArrayList<>();
        List<Document> attractions = new ArrayList<>();

        List<Integer> listOfCustomerIds = getCustomersIds(database);

        for (int i = 1; i <= numberOfRecords; i++) {

            program = new Program(faker);

            for (int j = 0; j < rd.nextInt(10) + 1; j++) {
                photos.add(new Document().append("path_name", faker.file().fileName()));
            }

            for (int j = 0; j < rd.nextInt(100) + 1; j++) {
                reviews.add(new Document()
                        .append("customer_id", listOfCustomerIds.get(rd.nextInt(listOfCustomerIds.size())))
                        .append("description", faker.lorem().fixedString(750))
                        .append("points", rd.nextInt(6))
                        .append("title", faker.lorem().fixedString(50)));
            }

            for (int j = 0; j < rd.nextInt(5) + 1; j++) {
                tours.add(new Document()
                        .append("date", faker.date().future(rd.nextInt(100) + 10, TimeUnit.HOURS))
                        .append("max_participants_nr", rd.nextInt(10) + 20)
                        .append("price", rd.nextDouble() * 10000));
            }

            for (int j = 0; j < rd.nextInt(15) + 1; j++) {
                attractions.add(new Document()
                        .append("description", faker.lorem().fixedString(750))
                        .append("price", rd.nextInt(150))
                        .append("title", faker.lorem().fixedString(50)));
            }

            document = new Document();
            document.append("title", program.getTitle());
            document.append("days_nr", program.getDays_nr());
            document.append("transport_type", program.getTransport_type());
            document.append("is_abroad", program.getIs_abroad());
            document.append("is_for_children", program.getIs_for_children());
            document.append("description", program.getDescription());
            document.append("photos", photos);
            document.append("attractions", attractions);
            document.append("reviews", reviews);
            document.append("tours", tours);
            database.getCollection("program").insertOne(document);
        }
    }

    public static List<Integer> getCustomersIds(MongoDatabase database) {
        MongoCollection<Document> collection = database.getCollection("customers");
        List<Document> documents = collection.find().into(new ArrayList<>());

        List<Integer> listOfCustomerIds = new ArrayList<>();

        for (Document doc : documents) {
            for (Map.Entry<String, Object> entry : doc.entrySet()) {
                if (entry.getValue().toString().length() < 6) {
                    listOfCustomerIds.add(Integer.valueOf(entry.getValue().toString()));
                }
            }
        }
        return listOfCustomerIds;
    }
}
