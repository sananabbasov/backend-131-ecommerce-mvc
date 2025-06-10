package az.edu.itbrains.fruitables.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String shortDescription;

    @Column(length = 10000)
    private String description;

    private String photoUrl;

    private Double quantity;

    private Double price;

    private Double discountPrice;

    @ManyToOne
    private Category category;
}
