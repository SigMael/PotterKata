
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Potter {
    public double processPrice(double[] prices) {
        List<Double> books = DoubleStream.of(prices).boxed().collect(Collectors.toList());
        if (books.size() == 0)
            return 0;

        if(books.size() == 1){
            return 8 * books.size();
        }

        if(books.size() == 2){
            if(books.contains(books.remove(0)))
                return 8 * 2;
            return 8 * 2 * 0.95;
        }

        if(books.size() == 3){
            if(books.get(0).equals(books.get(1)) && books.get(1).equals(books.get(2)))
                return 8*3;
            return 8 * 3 * 0.9;
        }

        return 0;
    }

}
