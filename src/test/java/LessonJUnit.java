import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.PageObjects;

import java.util.stream.Stream;

public class LessonJUnit extends TestBase {
    PageObjects giftCardPage = new PageObjects();

    @ParameterizedTest
    @ValueSource(strings = {"$5 Virtual Gift Card", "$25 Virtual Gift Card", "$50 Physical Gift Card", "$100 Physical Gift Card"})
    void searchGiftCard(String giftCard) {
        giftCardPage.openPage();
        giftCardPage.searchFor(giftCard);
        giftCardPage.checkSearchHeader("Search");
        giftCardPage.checkProductItem(giftCard);
    }

    @ParameterizedTest
    @CsvSource({
            "'$5 Virtual Gift Card', 1",
            "'$25 Virtual Gift Card', 1",
            "'$50 Physical Gift Card', 1",
            "'$100 Physical Gift Card', 1"
    })
    void searchGiftCardWithExpectedResults(String giftCard, int expectedResults) {
        giftCardPage.openPage();
        giftCardPage.searchFor(giftCard);
        giftCardPage.checkProductItem(giftCard);
        giftCardPage.checkProductItem(giftCard);
    }

    @ParameterizedTest
    @MethodSource("giftCardDataProvider")
    void searchGiftCardWithAvailabilityCheck(String giftCard, boolean isAvailable) {
        giftCardPage.openPage();
        giftCardPage.searchFor(giftCard);
        giftCardPage.checkProductItem(giftCard);
        giftCardPage.checkProductItem(giftCard);
    }

    static Stream<Arguments> giftCardDataProvider() {
        return Stream.of(
                Arguments.of("$5 Virtual Gift Card", true),
                Arguments.of("$25 Virtual Gift Card", true),
                Arguments.of("$50 Physical Gift Card", true),
                Arguments.of("$100 Physical Gift Card", true)
        );
    }
}
