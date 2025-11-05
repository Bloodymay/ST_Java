package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.sun.tools.javac.Main;
import common.Utilities;
import model.Contact;
import model.Group;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {
    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--count", "-c"})
    int count;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private void save(Object data) throws IOException {
        if (format.equals("json")) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output), data);
        } else if (format.equals("yaml")) {
            var mapper = new YAMLMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output), data);


        }else if (format.equals("xml")) {
            var mapper = new XmlMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output), data);


        } else {
            throw new IllegalArgumentException("Unknown format " + format);
        }

    }

//    private void saveAnotherWay(Object data) throws IOException {
//        if (format.equals("json")) {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.enable(SerializationFeature.INDENT_OUTPUT);
//            var json = mapper.writeValueAsString(data);
//            try (var writer = new FileWriter(output)) {
//                writer.write(json);
//            }
//
//        } else {
//            throw new IllegalArgumentException("Unknown format " + format);
//        }
//
//    }

    private Object generate() {
        if (type.equals("groups")) {
            return generateGroups();

        } else if (type.equals("contacts")) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }

    private Object generateContacts() {
        var result = new ArrayList<Contact>();
        for (int i = 0; i < count; i++) {
            result.add(new Contact()
                    .mainFields(Utilities.stringGenerator(i * 10), Utilities.stringGenerator(i * 10),Utilities.stringGenerator(i * 10),Utilities.phoneGenerator(10))
                    .contactWithPhoto(Utilities.getRandomFile("src/test/resources/images")));
        }
        return result;
    }

    private Object generateData(Supplier<Object> supplier) {
        return Stream.generate(supplier).limit(10).collect(Collectors.toList()); // через поток
//        var result = new ArrayList<Object>(); через цикл
//
//        for (int i = 0; i < count; i++) {
//            result.add(supplier.get());
//        }
//        return result;
    }

    private Object generateGroups() {
        var result = new ArrayList<Group>();
        for (int i = 0; i < count; i++) {
            result.add(new Group()
                    .withName(Utilities.stringGenerator(i * 10))
                    .withHeader(Utilities.stringGenerator(i * 10))
                    .withFooter(Utilities.stringGenerator(i * 10)));
        }
        return result;
    }
    private Object generateGroupsWithSupplier() {
        return generateData(() -> new Group()
                .withName(Utilities.stringGenerator(10))
                .withHeader(Utilities.stringGenerator(10))
                .withFooter(Utilities.stringGenerator(10)));

    }
    private Object generateContactsWithSupplier() {
        return generateData(() -> new Contact()
                .mainFields(Utilities.stringGenerator(10), Utilities.stringGenerator(10),Utilities.stringGenerator(10),Utilities.phoneGenerator(10))
                .contactWithPhoto(Utilities.getRandomFile("src/test/resources/images")));

    }
}
