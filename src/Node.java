public class Node {
    private State currentState;
    private Node previousNode;
    private Action actionToGetHere;

    public Node(State currentState, Node previousNode, Action actionToGetHere) {
        this.currentState = currentState;
        this.previousNode = previousNode;
        this.actionToGetHere = actionToGetHere;
    }

    public Node[] expand() {
        Action[] actions = currentState.actions();
        Node[] expansion = new Node[actions.length];
        for (int i = 0; i < actions.length; i++) {
            State newState = currentState.result(actions[i]);
            expansion[i] = new Node(newState, this, actions[i]);
        }
        return expansion;
    }

    public int heuristicValue() {
        return 0; //TODO: implement
    }
}
