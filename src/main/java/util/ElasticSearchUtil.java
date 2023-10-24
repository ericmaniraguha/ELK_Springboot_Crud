package util;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import java.util.function.Supplier;
import java.util.ArrayList;
import java.util.List;

public class ElasticSearchUtil {

    // Create a supplier for a simple matchAll query.
    public static Supplier<Query> supplier() {
        return () -> Query.of(q -> q.matchAll(matchAllQuery()));
    }

    // Create and return a MatchAllQuery.
    public static MatchAllQuery matchAllQuery() {
        return new MatchAllQuery.Builder().build();
    }

    // Create a supplier for a boolean query based on product name and quantity.
    public static Supplier<Query> supplierQueryForBoolQuery(String productName, Integer qty) {
        return () -> Query.of(q -> q.bool(boolQuery(productName, qty)));
    }

    // Create and return a BoolQuery with filter and must clauses.
    public static BoolQuery boolQuery(String productName, Integer qty) {
        return new BoolQuery.Builder()
                .filter(termQuery(productName))
                .must(matchQuery(qty))
                .build();
    }

    // Create and return a TermQuery for a specific product name.
    public static Query termQuery(String productName) {
        return Query.of(q -> q.term(new TermQuery.Builder().field("name").value(productName).build()));
    }

    // Create and return a MatchQuery for a specific quantity.
    public static Query matchQuery(Integer qty) {
        return Query.of(q -> q.match(new MatchQuery.Builder().field("qty").query(qty).build()));
    }
}
