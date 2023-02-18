package ciril.forest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Point {

    private int i;
    private int j;

    @Override
    public String toString() {
        return "(" + i + "," + j + ")";
    }
}
