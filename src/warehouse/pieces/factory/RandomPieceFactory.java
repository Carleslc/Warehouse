package warehouse.pieces.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import warehouse.Color;
import warehouse.pieces.Piece;
import warehouse.pieces.builder.PieceBuilder;

/**
 * A piece factory that creates pieces randomly from other piece factories.
 * 
 * This piece factory also will provide pieces painted with some colors randomly.
 * 
 * Piece references and storages are provided by other piece factories.
 */
public class RandomPieceFactory implements PieceFactory {
    
    /**
     * Piece factories to use randomly.
     */
    private PieceTypeFactory<?>[] pieceFactories;
    
    /**
     * Random object to provide random values, like random numbers or booleans.
     */
    private Random random = new Random();
    
    /**
     * Creates a new random piece factory that can create new pieces from other piece factories, selected randomly.
     * 
     * @param pieceFactories variable number of piece factories that can be used by this random piece factory
     */
    public RandomPieceFactory(PieceTypeFactory<?>... pieceFactories) {
        this.pieceFactories = pieceFactories;
    }
    
    /**
     * Pick randomly a piece factory and get a new builder to create a piece using that piece factory
     * 
     * @return a new builder to create a piece
     */
    private PieceBuilder.LastStep<?> getRandomPieceBuilder() {
        // Pick a piece factory randomly
        PieceTypeFactory<?> pieceFactory = pieceFactories[random.nextInt(pieceFactories.length)];
        return pieceFactory.newBuilder();
    }
    
    /**
     * Paint randomly the piece being created with different colors.
     * Pieces are painted with minimum 0 colors and maximum all available colors.
     * 
     * @param builder the builder that will create the piece
     * @return a builder using the provided builder with additional colors set
     */
    private PieceBuilder.LastStep<?> paintRandomly(PieceBuilder.LastStep<?> builder) {
        // Create a list with all available colors
        List<Color> colors = new ArrayList<>(List.of(Color.values()));
        
        // max available colors
        int maxColors = colors.size();
        
        // Paint (or not) the piece being created maxColors times
        for (int i = 0; i < maxColors; i++) {
            // Decide randomly if paint or not to paint the piece with another color
            boolean withColor = random.nextBoolean();
            
            if (withColor) {
                // Select a randomly selected index from the list of available colors
                int randomColorIndex = random.nextInt(colors.size());
                
                // Retrieve the selected color and remove it from the list of available colors so next colors are different
                Color color = colors.remove(randomColorIndex);
                
                // Get the next builder with the selected color set
                builder = builder.paint(color);
            }
        }
        
        // return the builder with colors set
        return builder;
    }
    
    /**
     * Create a new piece of some type with a reference and a storage where this piece should be stored,
     * randomly painted with some different colors.
     * 
     * @return a new randomly created piece instance
     */
    @Override
    public Piece create() {
        return paintRandomly(getRandomPieceBuilder()).build();
    }
}
