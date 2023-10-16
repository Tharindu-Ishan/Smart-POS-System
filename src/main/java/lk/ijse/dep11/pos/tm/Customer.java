package lk.ijse.dep11.pos.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String id;
    private String name;
    private String address;

    @Override
    public String toString() {
        return id;
    }
}
