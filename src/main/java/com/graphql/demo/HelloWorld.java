package com.graphql.demo;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

public class HelloWorld {

    public static void main(String[] args) {
        //schema
        String schema = "type Query{hello: String}";
        //schema 解析器
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);
        //运行时，将查询结果使用静态数据获取器获取预先设定好的数据
        RuntimeWiring runtimeWiring = newRuntimeWiring()
                .type("Query", builder -> builder.dataFetcher("hello", new StaticDataFetcher("world")))
                .build();
        //生成可执行的Schema
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        //构建GraphQL
        GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
        //执行
        ExecutionResult executionResult = build.execute("{hello}");
        //输出结果
        System.out.println(executionResult.getData().toString());
    }
}
