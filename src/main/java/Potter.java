
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
        ArrayList<ArrayList<Integer>> bookSets = new ArrayList<>();
        bookSets.add(getBookSet(lesserOccurence));
		if(isThereAdditionalOccurences(books.size())) {
			bookSets.add(getBookSet(lesserOccurence));
        }
        
        return calculFinalPrice(books, bookSets);
    }

	private boolean isThereAdditionalOccurences(int bookNumbers) {
		boolean isThereAtLeast1Occurence = false;
		for (int i = 1; i < bookNumbers; i++) {
			if(occurences.contains(i)) {
				if(isThereAtLeast1Occurence) {
					System.out.println("Additional ocurence yes");
					return true;					
				}
				isThereAtLeast1Occurence = true;
			}
		}
		return false;
	}

	private double calculFinalPrice(List<Integer> books, ArrayList<ArrayList<Integer>>bookSets) {
		double finalReduction =0;
		double finalPrice =0;
		for (ArrayList<Integer> bookSet : bookSets) {
			if(bookSets.size() > 1) {
				finalReduction += reduction[bookSet.size() - 2];
				finalPrice += finalReduction + (books.size() - bookSet.size()) * 8;
			} 
			else {
				finalPrice += books.size() * 8;        	
			}			
		}
		return finalPrice;
	}

	private ArrayList<Integer> getBookSet(int lesserOccurence) {
		ArrayList<Integer> bookSet = new ArrayList<>();
		ArrayList<Boolean> isBookNumberAlreadyInSet = new ArrayList<>();
		System.out.println(occurences.toString());
		
		for (int i = 0; i < BOOK_NUMBERS.length; i++) {
			isBookNumberAlreadyInSet.add(false);
		}
		
        for (int i = 0; i < lesserOccurence; i++) {
			if(occurences.get(0) >= i + 1 && !isBookNumberAlreadyInSet.get(0)) {
				bookSet.add(occurences.get(0));
				occurences.set(0, occurences.get(0) - 1);
				isBookNumberAlreadyInSet.set(0, true);
			}
			if(occurences.get(1) >= i + 1  && !isBookNumberAlreadyInSet.get(1)) {
				bookSet.add(occurences.get(1));
				occurences.set(1, occurences.get(1) - 1);
				isBookNumberAlreadyInSet.set(1, true);
			}
			if(occurences.get(2) >= i + 1  && !isBookNumberAlreadyInSet.get(2)) {
				bookSet.add(occurences.get(2));
				occurences.set(2, occurences.get(2) - 1);
				isBookNumberAlreadyInSet.set(2, true);
			}
			if(occurences.get(3) >= i + 1  && !isBookNumberAlreadyInSet.get(3)) {
				bookSet.add(occurences.get(3));
				occurences.set(3, occurences.get(3) - 1);
				isBookNumberAlreadyInSet.set(3, true);
			}
			if(occurences.get(4) >= i + 1  && !isBookNumberAlreadyInSet.get(4)) {
				bookSet.add(occurences.get(4));
				occurences.set(4, occurences.get(4) - 1);
				isBookNumberAlreadyInSet.set(4, true);
			}
		}
        System.out.println("setOfBook="+bookSet.toString());
		return bookSet;
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
