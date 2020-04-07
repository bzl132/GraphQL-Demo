package com.graphql.demo;

import com.google.common.io.Resources;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SchemaDemo {


    public static void main(String[] args) {
//        SchemaParser schemaParser = new SchemaParser();
//        SchemaGenerator schemaGenerator = new SchemaGenerator();
//
//        File schemaFile = loadSchema("starWarsSchema.graphqls");
//
//        TypeDefinitionRegistry typeRegistry = schemaParser.parse(schemaFile);
//        RuntimeWiring wiring = buildRuntimeWiring();
//        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, wiring);
    }

//    private static File loadSchema(String path) {
//        return Resources.getResource(path);
//    }


//    private static RuntimeWiring buildRuntimeWiring() {
//        return RuntimeWiring.newRuntimeWiring()
//                .scalar(CustomScalar)
//                // this uses builder function lambda syntax
//                .type("QueryType", typeWiring -> typeWiring
//                        .dataFetcher("hero", new StaticDataFetcher(StarWarsData.getArtoo()))
//                        .dataFetcher("human", StarWarsData.getHumanDataFetcher())
//                        .dataFetcher("droid", StarWarsData.getDroidDataFetcher())
//                )
//                .type("Human", typeWiring -> typeWiring
//                        .dataFetcher("friends", StarWarsData.getFriendsDataFetcher())
//                )
//                // you can use builder syntax if you don't like the lambda syntax
//                .type("Droid", typeWiring -> typeWiring
//                        .dataFetcher("friends", StarWarsData.getFriendsDataFetcher())
//                )
//                // or full builder syntax if that takes your fancy
//                .type(
//                        newTypeWiring("Character")
//                                .typeResolver(StarWarsData.getCharacterTypeResolver())
//                                .build()
//                )
//                .build();
//    }

}
