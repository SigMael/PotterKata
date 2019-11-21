
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Potter {
	private final int[] BOOK_NUMBERS = {0, 1, 2, 3, 4};
	private final double BOOK_STANDARD_PRICE = 8;
	private final double REDUCTION_LVL_1 =  BOOK_STANDARD_PRICE * 2 * 0.95;
	private final double REDUCTION_LVL_2 =  BOOK_STANDARD_PRICE * 3 * 0.9;
	private final double REDUCTION_LVL_3 =  BOOK_STANDARD_PRICE * 4 * 0.8;
	private final double REDUCTION_LVL_4 =  BOOK_STANDARD_PRICE * 5 * 0.75;
	
	private final double[] reduction = {
			REDUCTION_LVL_1,
			REDUCTION_LVL_2,
			REDUCTION_LVL_3,
			REDUCTION_LVL_4
	};
	
	private ArrayList<Integer> occurences;
    public double processPrice(int[] prices) {
        List<Integer> books = IntStream.of(prices).boxed().collect(Collectors.toList());
        if (books.isEmpty())
        	return 0;
                
        if(books.size() == 1){
            return BOOK_STANDARD_PRICE * books.size();
        }

        readOccurences(books);
        
        int lesserOccurence = getLesserOccurence(books);
        System.out.println(lesserOccurence);
        int bookInSet = getBookSet(lesserOccurence);
        System.out.println("setOfBook="+bookInSet);
        return calculFinalPrice(books, bookInSet);
    }

	private double calculFinalPrice(List<Integer> books, int bookInSet) {
		double finalReduction =0;
        if(bookInSet > 0)
        	finalReduction = reduction[bookInSet - 2];
        double finalPrice = finalReduction + (books.size() - bookInSet) * 8;
		return finalPrice;
	}

	private int getBookSet(int lesserOccurence) {
		int bookInSet = 0;
        for (int i = 0; i < lesserOccurence; i++) {
        	bookInSet = 0;
			if(occurences.get(0) >= i + 1) {
				occurences.set(0, occurences.get(0) - 1);
				bookInSet++;
			}
			if(occurences.get(1) >= i + 1) {
				occurences.set(1, occurences.get(1) - 1);
				bookInSet++;		
			}
			if(occurences.get(2) >= i + 1) {
				occurences.set(2, occurences.get(2) - 1);
				bookInSet++;
			}
			if(occurences.get(3) >= i + 1) {
				occurences.set(3, occurences.get(3) - 1);
				bookInSet++;
			}
			if(occurences.get(4) >= i + 1) {
				occurences.set(4, occurences.get(4) - 1);
				bookInSet++;
			}
		}
		return bookInSet;
	}

	private int getLesserOccurence(List<Integer> books) {
		int lesserOccurence = books.size();
        for (Integer occurence: occurences) {
        	if(occurence != 0)
        		lesserOccurence = occurence < lesserOccurence ? occurence : lesserOccurence;
		}
        return lesserOccurence;
	}
	
	private void readOccurences(List<Integer> books) {
		this.occurences = new ArrayList<>();
		
		for (int i = 0; i < BOOK_NUMBERS.length; i++) {
			this.occurences.add(getOccurence(books, BOOK_NUMBERS[i]));
		}
	}

	private int getOccurence(List<Integer> books, int val) {
		int occurenceBook = Collections.frequency(books, val);
		return occurenceBook;
	}

}
