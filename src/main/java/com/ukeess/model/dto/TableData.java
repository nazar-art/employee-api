package com.ukeess.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.List;

/**
 * @author Nazar Lelyak.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableData {

    private String tableName;
    private String id;
    private String name;
    @Singular
    private List<String> fields;
}
