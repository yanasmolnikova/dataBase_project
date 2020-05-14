package com.example.demo;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@Route("") // localhost:8080/
public class MainView extends Composite<VerticalLayout> {

    private final CarRepository carRepository;

    private Grid<Car> grid = new Grid<>();
    private TextField num = new TextField("Number");
    private TextField color = new TextField("Color");
    private TextField mark = new TextField("Mark");
    private Button save = new Button("Save", VaadinIcon.CHECK.create());
    private Button create = new Button("New", VaadinIcon.PLUS.create());
    private Button delete = new Button("Delete", VaadinIcon.MINUS.create());
    private VerticalLayout form = new VerticalLayout(num, color, mark, save);

    private Binder<Car> binder = new Binder<>(Car.class);
    private Car car;

    public MainView(CarRepository carRepository) {
        this.carRepository = carRepository;

        grid.addColumn(Car::getNum).setHeader("Number");
        grid.addColumn(Car::getColor).setHeader("Color");
        grid.addColumn(Car::getMark).setHeader("Mark");
        grid.addSelectionListener(event -> setCar(grid.asSingleSelect().getValue()));
        updateGrid();

        save.addClickListener(event -> saveClicked());
        create.addClickListener(event -> createClicked());
        delete.addClickListener(event -> deleteClicked());

        getContent().add(grid, create, delete, form);
        binder.bindInstanceFields(this);
        binder.setBean(null);
    }

    private void createClicked() {
        grid.asSingleSelect().clear();
        setCar(new Car());
    }

    private void deleteClicked() {
        binder.readBean(car);
        setCar(car);
        carRepository.delete(car);
        updateGrid();
        Notification.show("Deleted!");
    }

    private void setCar(Car car) {
        this.car = car;
        form.setEnabled(car != null);
        binder.setBean(car);
    }

    private void saveClicked() {
        binder.readBean(car);
        if (car.getId() == null) {
            carRepository.create(car);
        } else {
            carRepository.update(car);
        }
        updateGrid();
        Notification.show("Saved!");
    }

    private void updateGrid() {
        grid.setItems(carRepository.findAll());
    }

}
