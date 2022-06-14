package com.blackphoenixproductions.forumfrontend.dto;


import com.blackphoenixproductions.forumfrontend.enums.BooleanOperator;
import com.blackphoenixproductions.forumfrontend.enums.QueryOperator;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Filter {
    BooleanOperator booleanOperator;
    QueryOperator queryOperator;
    String field;
    String value;
    List<String> values; // in caso di operatore IN e BETWEEN
    @Builder.Default List<Filter> filters = new ArrayList<>();
}
