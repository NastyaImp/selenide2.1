import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldDataInForm() {
        Configuration.holdBrowserOpen=true;
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Уфа");
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys. DELETE);
        $("[data-test-id=date] input").val(date);
        $("[name='name']").setValue("Анна-Мария Кузнецова");
        $("[name='phone']").setValue("+79232453487");
        $(By.className("checkbox__text")).click();
        $(By.className("button__content")).click();
        $(By.className("notification__content")).shouldHave(Condition.exactText("Встреча успешно забронирована на"+" "+ date),
                Duration.ofSeconds(15));



    }
}
