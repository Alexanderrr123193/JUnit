package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PageObjects {
    private final SelenideElement searchInput = $("#small-searchterms");
    private final SelenideElement searchButton = $("input[type='submit']");
    private final SelenideElement productItem = $(".product-item");
    private final SelenideElement searchHeader = $("h1");

    public void openPage() {
        open("https://demowebshop.tricentis.com");
    }

    public void searchFor(String giftCard) {
        searchInput.setValue(giftCard);
        searchButton.click();
    }

    public void checkSearchHeader(String expectedHeader) {
        searchHeader.shouldHave(text(expectedHeader));
    }

    public void checkProductItem(String giftCard) {
        productItem.shouldHave(text(giftCard));
    }
}
