public class Node {
    private State state;
    private Node parent;
    private Action action;

    public State getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public Action getAction() {
        return action;
    }

    public Node(State state, Node parent, Action action) {
        this.state = state;
        this.parent = parent;
        this.action = action;
    }

    public Node[] expand() {
        Action[] actions = state.actions();
        Node[] expansion = new Node[actions.length];
        for (int i = 0; i < actions.length; i++) {
            State newState = state.result(actions[i]);
            expansion[i] = new Node(newState, this, actions[i]);
        }
        return expansion;
    }

    public int heuristicValue() {
        return 0; //TODO: implement
    }
}
