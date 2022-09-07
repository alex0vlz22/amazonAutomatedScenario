package utils.enums;

public enum SortByEnum {

    FEATURED("Featured", "popularity-rank"),
    PRICE_LOW_TO_HIGH("Price: Low to High", "price-asc-rank"),
    PRICE_HIGH_TO_LOW("Price: High to Low", "price-desc-rank"),
    AVG_CUSTOMER_REVIEW("Avg. Customer Review", "review-rank"),
    NEWEST_ARRIVALS("Newest Arrivals", "date-desc-rank");

    SortByEnum(String sortBy, String optionValue) {
        this.sortBy = sortBy;
        this.optionValue = optionValue;
    }

    private String sortBy;

    private String optionValue;

    public String getSortBy() {
        return sortBy;
    }

    public String getOptionValue() {
        return optionValue;
    }

}
