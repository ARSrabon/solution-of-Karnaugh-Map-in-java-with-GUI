import java.util.Set;

/**
 * Created by msrabon on 11-Oct-16.
 * Project Name: K-Map.
 */
public class KMapUTIL {
    public KMapUTIL() {
        IO io = new IO();
        Set<Character> totalVariables = io.totalVariables;
        StringBuilder terms = io.terms;
        KarnaughMap kMap = new KarnaughMap();
        boolean[][] map = kMap.buildMap(totalVariables, terms);
        char[] vocabulary = kMap.vocabulary;
        io.displayMap(map, vocabulary);
        StringBuilder solution = kMap.solveKMap();
        io.displaySolution(solution);
    }
}