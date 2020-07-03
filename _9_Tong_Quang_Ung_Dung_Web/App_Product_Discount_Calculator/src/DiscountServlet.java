import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/discount")
public class DiscountServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String des = request.getParameter("des");
        float price = Float.parseFloat(request.getParameter("price"));
        float discount = Float.parseFloat(request.getParameter("discount"));

        float AmountDiscount = (float)(price * discount * 0.01);
        float discountPrice = price - AmountDiscount;

        request.setAttribute("amountDiscount", AmountDiscount);
        request.setAttribute("discountPrice", discountPrice);
        try {
            getServletContext().getRequestDispatcher("/display-discount.jsp").forward(request, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ServletException ex) {
            ex.printStackTrace();
        }
    }
}
