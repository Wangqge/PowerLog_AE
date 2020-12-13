package socialite.async.codegen;

import java.util.Objects;
import java.util.*;
public class WeightPair {
    private List<Pair> adj;
    private List<Double> weightList;
    private int hashCode = -1;
    private WeightPair(){

    }
    public WeightPair(List<Pair> adj_, List<Double> weightList_){
        this.adj=adj_;
        this.weightList=weightList_;
    }
    public String toString() {
        return adj.toString() + " " + weightList.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightPair that = (WeightPair) o;
        return hashCode == that.hashCode &&
                Objects.equals(adj, that.adj) &&
                Objects.equals(weightList, that.weightList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(adj, weightList, hashCode);
    }

    public List<Pair> getadjList() {
        return this.adj;
    }

    public List<Double> getweightList() {
        return this.weightList;
    }

}
