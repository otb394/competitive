package code;

import io.InputReader;
import FastIO.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PortfolioMatching {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String line = in.readLine();
        String[] tokens = line.split(":");
        String portfolioString = tokens[0];
        String benchmarkString = tokens[1];
        List<Asset> portfolio = extractAssets(portfolioString);
        List<Asset> benchmark = extractAssets(benchmarkString);
        List<Transaction> transactions = getTransactions(portfolio, benchmark);
        for (Transaction transaction : transactions) {
            out.println(transaction);
        }
    }

    private List<Transaction> getTransactions(List<Asset> portfolio, List<Asset> benchmark) {
        Map<Asset.AssetId, Double> portfolioMap = new HashMap<>();
        for (Asset asset: portfolio) {
            portfolioMap.put(asset.getId(), asset.getShares());
        }

        List<Transaction> transactions = new ArrayList<>();
        Set<Asset.AssetId> benchmarkSet = new HashSet<>();

        for (Asset asset : benchmark) {
            benchmarkSet.add(asset.getId());
            double portfolioShares = portfolioMap.getOrDefault(asset.getId(), 0.0);
            double benchmarkShares = asset.getShares();
            if (portfolioShares != benchmarkShares) {
                Asset transactionAsset = new Asset(asset.getId(), Math.abs(portfolioShares - benchmarkShares));
                if (benchmarkShares > portfolioShares) {
                    transactions.add(new Transaction(Transaction.TransactionType.BUY, transactionAsset));
                } else {
                    transactions.add(new Transaction(Transaction.TransactionType.SELL, transactionAsset));
                }
            }
        }

        for (Asset asset : portfolio) {
            if (!benchmarkSet.contains(asset.getId())) {
                transactions.add(new Transaction(Transaction.TransactionType.SELL, asset));
            }
        }

        return transactions.stream().sorted().collect(Collectors.toList());
    }

    private List<Asset> extractAssets(String description) {
        String[] tokens = description.split("\\|");
        return Arrays.stream(tokens).map(Asset::new).collect(Collectors.toList());
    }

    private static class Asset {
        private AssetId id;
        private double shares;

        Asset(String description) {
            String[] tokens = description.split(",");
            this.id = new AssetId(tokens[0], tokens[1]);
            this.shares = Double.parseDouble(tokens[2]);
        }

        Asset(AssetId id, double shares) {
            this.id = id;
            this.shares = shares;
        }

        private enum AssetType {
            BOND, STOCK;
        }

        private static class AssetId implements Comparable<AssetId> {
            private String name;
            private AssetType type;

            AssetId(String name, String type) {
                this.name = name;
                this.type = AssetType.valueOf(type);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                AssetId assetId = (AssetId) o;
                return name.equals(assetId.name) &&
                        type == assetId.type;
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, type);
            }

            @Override
            public int compareTo(AssetId o) {
                if (name.equals(o.name)) {
                    return type.compareTo(o.type);
                }
                return name.compareTo(o.name);
            }

            public String getName() {
                return name;
            }

            public AssetType getType() {
                return type;
            }
        }

        public AssetId getId() {
            return id;
        }

        double getShares() {
            return shares;
        }
    }

    private static class Transaction implements Comparable<Transaction> {
        private TransactionType type;
        private Asset asset;

        private enum TransactionType {
            BUY, SELL;
        }

        Transaction(TransactionType type, Asset asset) {
            this.type = type;
            this.asset = asset;
        }

        @Override
        public int compareTo(Transaction o) {
            return asset.getId().compareTo(o.asset.getId());
        }

        @Override
        public String toString() {
            String shares = (asset.getShares() == Math.floor(asset.getShares()))
                    ? Integer.toString((int)asset.getShares()) : Double.toString(asset.getShares());
            return String.format("%s,%s,%s,%s", type.name(), asset.getId().getName(), asset.getId().getType().name(),
                    shares);
        }
    }
}
