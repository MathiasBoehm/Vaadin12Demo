package de.struktuhr.orderapp.web;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import de.struktuhr.orderapp.entity.Customer;
import de.struktuhr.orderapp.repo.CustomerRepository;

import java.util.UUID;

@Route("images")
public class ImageView extends VerticalLayout {

    public ImageView(CustomerRepository customerRepository) {
        // Add Grid with Images
        Grid<Customer> grid = new Grid<>();

        grid.setItems(customerRepository.findAll());
        grid.addColumn(Customer::getId).setHeader("ID").setFlexGrow(0);
        grid.addColumn(Customer::getFirstName).setHeader("First Name");
        grid.addColumn(Customer::getLastName).setHeader("Last Name");
        grid.addComponentColumn((customer) -> {
            Image image = new Image(customer.getImageUrl(), "Alt Text");
            image.setHeight("64px");
            image.setWidth("64px");
            image.setTitle(customer.getFirstName());
            return image;
        }).setHeader("Image");
        grid.setHeightByRows(true);
        add(grid);

        // Add Simple Image
        add(new Image("http://loremflickr.com/320/240?random=" + UUID.randomUUID().toString(), "Image"));
    }


}
