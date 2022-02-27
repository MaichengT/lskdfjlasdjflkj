// --== CS400 Project One File Header ==--
// Name: Braeden Bertz
// CSL Username: bbertz
// Email: bbertz@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: Algorithm Engineer did not communicate with me, Frontend Engineer did
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class HashTableSortedSets<KeyType,ValueType>{
    ArrayList<ValueType> addedShows = new ArrayList<>();

    IShow[] testShows = {new IShow("Attack on Titan", "Netflix Hulu", 2002, 99),
            new IShow("Adventure Time: with Finn and Jake", "Disney+", 2, 1),
            new IShow("M.D.D.E.L.", "Netflix Prime Video", 12098, 290382),
            new IShow("Jake and Bake, the titan shifter", "Prime Video", 999, 999),
            new IShow("Snakes on a Plane: And DONUTS!!!", "Netflix", 2002,2092),
    };

    public void add(KeyType key, ValueType value) {
        addedShows.add(value);
    }


    public boolean put(KeyType key, List<ValueType> value) {
        return false;
    }


    public List<ValueType> get(KeyType key) throws NoSuchElementException {
        if (key.equals("and")) {
            IShow[] retVal = new IShow[]{testShows[1], testShows[3], testShows[4]};
            return (List<ValueType>) new ArrayList<IShow>(Arrays.asList(retVal));//problem, cannot add or remove from this list
        }
        if (key.equals(2002)) {
            IShow[] retVal = new IShow[]{testShows[0], testShows[4]};

            return (List<ValueType>) new ArrayList<IShow>(Arrays.asList(retVal));
        }
        if (key.equals("i")){
            IShow[] retVal = new IShow[]{testShows[0], testShows[1], testShows[2], testShows[3]};
            return (List<ValueType>) new ArrayList<IShow>(Arrays.asList(retVal));
        }
        return null;
    }


    public int size() {
        return addedShows.size();
    }


    public boolean containsKey(KeyType key) {
        return false;
    }


    public List<ValueType> remove(KeyType key) {
        return null;
    }


    public void clear() {

    }
}
