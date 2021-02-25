package code;

import FastIO.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GHC21PracticeProblem {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int m = in.nextInt();
        int nt = in.nextInt();
        int nh = in.nextInt();
        int nf = in.nextInt();
        List<Pizza> pizzas = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int ni = in.nextInt();
            Pizza pizza = new Pizza();
            pizza.id = i;
            for (int j = 0; j < ni; j++) {
                String ing = in.nextString();
                pizza.ingredients.add(ing);
            }
            pizzas.add(pizza);
        }
        pizzas.sort(Comparator.comparingInt((Pizza p) -> p.ingredients.size()).reversed());
        List<Delivery> deliveries = new ArrayList<>();
        int pi = 0;
        for (int i = 0; i < nf && (pi <= (m-4)); i++) {
            Delivery delivery = new Delivery();
            delivery.people = 4;
            for (int j = 0; j < 4; j++) {
                delivery.pizzas.add(pizzas.get(pi));
                pi++;
            }
            deliveries.add(delivery);
        }
        for (int i = 0; i < nh && (pi <= (m-3)); i++) {
            Delivery delivery = new Delivery();
            delivery.people = 3;
            for (int j = 0; j < 3; j++) {
                delivery.pizzas.add(pizzas.get(pi));
                pi++;
            }
            deliveries.add(delivery);
        }
        for (int i = 0; i < nt && (pi <= (m-2)); i++) {
            Delivery delivery = new Delivery();
            delivery.people = 2;
            for (int j = 0; j < 2; j++) {
                delivery.pizzas.add(pizzas.get(pi));
                pi++;
            }
            deliveries.add(delivery);
        }

        out.println(deliveries.size());
        for (Delivery delivery : deliveries) {
            out.print(delivery.people);
            for (Pizza pizza : delivery.pizzas) {
                out.printf(" %d", pizza.id);
            }
            out.println();
        }
    }

    private static class Pizza {
        public int id;
        public Set<String> ingredients;

        public Pizza() {
            this.ingredients = new TreeSet<>();
        }
    }

    private static class Delivery {
        public int people;
        public List<Pizza> pizzas;

        public Delivery() {
            this.pizzas = new ArrayList<>();
        }
    }

}
