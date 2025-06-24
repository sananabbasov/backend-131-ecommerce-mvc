package az.edu.itbrains.fruitables.payloads;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginationPayload<TModel> {
    private List<TModel> models;
    private Integer currentPage;
    private Integer totalPage;
}
