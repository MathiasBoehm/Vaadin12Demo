package de.struktuhr.demoapp.web;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import de.struktuhr.demoapp.ViewUtils;
import de.struktuhr.demoapp.entity.Customer;
import de.struktuhr.demoapp.repo.CustomerRepository;
import de.struktuhr.demoapp.web.layout.PolymerAppLayout;

import java.util.Comparator;
import java.util.UUID;

@Route(value = "images", layout = PolymerAppLayout.class)
public class ImageView extends VerticalLayout {

    public ImageView(CustomerRepository customerRepository) {
        // Add Grid with Images
        Grid<Customer> grid = new Grid<>();

        grid.setItems(customerRepository.findAll());
        grid.addColumn(Customer::getId).setHeader("ID").setFlexGrow(0);
        grid.addColumn(Customer::getFirstName).setHeader("First Name").setSortable(true);
        grid.addColumn(Customer::getLastName).setHeader("Last Name").setSortable(true);

        Grid.Column<Customer> birthday_calc = grid.addColumn((customer) -> ViewUtils.formatDate(customer.getBirthday())).setHeader("Birthday").setSortable(true);
        birthday_calc.setComparator(Comparator.comparing(Customer::getBirthday));
        birthday_calc.setTextAlign(ColumnTextAlign.START);

        Grid.Column<Customer> salary_calc = grid.addColumn((customer) -> ViewUtils.formatCurrency(customer.getSalary())).setHeader("Salary").setSortable(true);
        salary_calc.setComparator(Comparator.comparing(Customer::getSalary));
        salary_calc.setTextAlign(ColumnTextAlign.END);

        grid.addComponentColumn((customer) -> {
            Image image = new Image(customer.getImageUrl(), "Alt Text");
            image.setHeight("64px");
            image.setWidth("64px");
            image.setTitle(customer.getFirstName());
            return image;
        }).setHeader("Image").setTextAlign(ColumnTextAlign.CENTER);

        grid.setHeightByRows(true);
        add(grid);

        // Add Simple Image
        add(new Image("http://loremflickr.com/320/240?random=" + UUID.randomUUID().toString(), "Image"));
    }


}
