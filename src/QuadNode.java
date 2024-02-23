public class QuadNode {
    private static EmptyNode flyweight = null;

    private class EmptyNode {

        private EmptyNode() {

        }


        public EmptyNode getInstance() {
            if (flyweight == null) {
                return new EmptyNode();
            }
            return flyweight;
        }
    }
}
