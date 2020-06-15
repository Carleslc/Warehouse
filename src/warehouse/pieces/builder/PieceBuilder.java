package warehouse.pieces.builder;

import warehouse.Color;
import warehouse.pieces.ColoredPiece;
import warehouse.pieces.CylindricalPiece;
import warehouse.pieces.Piece;
import warehouse.pieces.RoundPiece;
import warehouse.pieces.SquarePiece;
import warehouse.storage.Storage;

/**
 * A Piece Builder, using Builder pattern to create pieces step by step.
 */
public class PieceBuilder {

    /**
     * To create a new piece builder the {@code newBuilder} method should be used,
     * so this constructor is private to this class.
     */
    private PieceBuilder() {}

    /**
     * Create a new piece builder, enforcing with interfaces the use of a step by step
     * creation approach for the mandatory fields (starting with the reference, then the storage,
     * then the type of the piece to build, and finally other optional methods like colors).
     * This ensures the build method is called when all mandatory fields are provided.
     * 
     * @return a new piece builder with configuration methods defined by the {@code ReferenceStep} interface
     */
    public static ReferenceStep newBuilder() {
        return new PieceStepBuilder();
    }
    
    // Inner interfaces to represent each step

    /**
     * First piece creation step, providing the reference of the piece to create.
     */
    public static interface ReferenceStep {
        
        /**
         * Provide a reference for the piece being created.
         * 
         * @param reference an integer reference
         * @return the builder at the storage step
         */
        StorageStep withReference(int reference);
    }

    /**
     * Second piece creation step, providing the storage where the piece to create should be stored.
     */
    public static interface StorageStep {
        
        /**
         * Provide a storage where the piece being created should be stored.
         * 
         * @param storage the storage where the piece should be stored
         * @return the builder at the type step
         */
        TypeStep shouldStoreAt(Storage<Piece> storage);
    }

    /**
     * Third piece creation step, providing the type of the piece to create.
     */
    public static interface TypeStep {
        
        /**
         * The piece must be a round piece.
         * 
         * @return the builder at the last step
         */
        PieceStepBuilder.BuildStep<RoundPiece> round();

        /**
         * The piece must be a square piece.
         * 
         * @return the builder at the last step
         */
        PieceStepBuilder.BuildStep<SquarePiece> square();

        /**
         * The piece must be a cylindrical piece.
         * 
         * @return the builder at the last step
         */
        PieceStepBuilder.BuildStep<CylindricalPiece> cylindrical();
    }

    /**
     * Optional step, providing a color to paint the piece being created.
     */
    public static interface ColorStep {
        
        /**
         * Provide a color to paint the piece being created.
         * 
         * @param color a color to paint the piece
         * @return the builder at the last step
         */
        PieceStepBuilder.BuildStep<ColoredPiece> paint(Color color);
    }

    /**
     * At this step all mandatory fields are set, so the piece can be actually build.
     * 
     * This interface includes optional configuration methods like setting colors.
     * 
     * @param <PieceType> a generic type standing for the subclass of the piece to create
     */
    public static interface LastStep<PieceType extends Piece> extends ColorStep {
        
        /**
         * Get the piece configured with this builder.
         * 
         * @return the piece created with this builder
         */
        PieceType build();
    }

    // Inner classes implementing the interfaces defined above
    
    /**
     * The piece builder implementing all step interfaces.
     */
    private static class PieceStepBuilder implements ReferenceStep, StorageStep, TypeStep {

        /**
         * Reference for the piece to create.
         */
        private int reference;

        /**
         * Storage where the piece to create needs to be stored.
         */
        private Storage<Piece> storage;

        /**
         * Provide a reference for the piece being created.
         * 
         * @param reference an integer reference
         * @return the builder at the storage step
         */
        @Override
        public StorageStep withReference(int reference) {
            this.reference = reference;
            return this;
        }

        /**
         * Provide a storage where the piece being created should be stored.
         * 
         * @param storage the storage where the piece should be stored
         * @return the builder at the type step
         */
        @Override
        public TypeStep shouldStoreAt(Storage<Piece> storage) {
            this.storage = storage;
            return this;
        }

        /**
         * The piece must be a round piece.
         * 
         * @return the builder at the last step
         */
        @Override
        public BuildStep<RoundPiece> round() {
            return new BuildStep<>(new RoundPiece(reference, storage));
        }

        /**
         * The piece must be a square piece.
         * 
         * @return the builder at the last step
         */
        @Override
        public BuildStep<SquarePiece> square() {
            return new BuildStep<>(new SquarePiece(reference, storage));
        }

        /**
         * The piece must be a cylindrical piece.
         * 
         * @return the builder at the last step
         */
        @Override
        public BuildStep<CylindricalPiece> cylindrical() {
            return new BuildStep<>(new CylindricalPiece(reference, storage));
        }

        /**
         * The last step of this builder, implementing build method and optional configuration methods.
         * 
         * @param <PieceType> a generic type standing for the subclass of the piece to create
         */
        public class BuildStep<PieceType extends Piece> implements LastStep<PieceType> {

            /**
             * The piece being created.
             */
            private PieceType piece;

            /**
             * Create a last step builder with a piece with mandatory fields already set.
             * 
             * @param piece the piece being created
             */
            private BuildStep(PieceType piece) {
                this.piece = piece;
            }

            /**
             * Provide a color to paint the piece being created.
             * 
             * @param color a color to paint the piece
             * @return the builder at the last step, which provides a colored piece
             */
            @Override
            public BuildStep<ColoredPiece> paint(Color color) {
                return new BuildStep<>(new ColoredPiece(piece, color));
            }

            /**
             * Get the piece configured with this builder.
             * 
             * @return the piece created with this builder
             */
            @Override
            public PieceType build() {
                return piece;
            }
        }
    }
}
