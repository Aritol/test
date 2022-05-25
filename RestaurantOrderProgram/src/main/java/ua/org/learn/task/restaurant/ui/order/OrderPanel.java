package ua.org.learn.task.restaurant.ui.order;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.ModifyType;
import ua.org.learn.task.restaurant.constant.State;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.dao.OrderDao;
import ua.org.learn.task.restaurant.model.Order;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.ui.main.MainForm;
import ua.org.learn.task.restaurant.ui.util.UiComponentUtil;

import javax.swing.*;
import java.sql.Date;
import java.time.Instant;

public class OrderPanel extends JPanel {
    private User currentUser;

    private final JButton cancelOrder;
    private final JButton createOrder;
    private final JButton detailOrder;
    private final JButton editOrder;
    private final JTable orderTable;
    private final OrderTableModel orderTableModel;

    public OrderPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        orderTableModel = new OrderTableModel();
        orderTable = UiComponentUtil.createTable(orderTableModel);
        add(new JScrollPane(orderTable));

        JPanel userActionPanel = new JPanel();
        userActionPanel.setLayout(new BoxLayout(userActionPanel, BoxLayout.LINE_AXIS));
        add(userActionPanel);

        detailOrder = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_ORDER_DETAIL),
                event -> {
                    Order selectedOrder = orderTableModel.getOrderByRow(orderTable.getSelectedRow());
                    if (selectedOrder != null) {
                        modifyOrder(selectedOrder, ModifyType.DETAIL);
                    }
                }
        );
        userActionPanel.add(detailOrder);

        createOrder = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_ORDER_CREATE),
                event -> {
                    OrderDao.createOrder(createNewOrder());
                    orderTableModel.fireTableDataChanged();
                    Order selectedOrder = OrderDao.getLastOrder();
                    if (selectedOrder != null) {
                        JOptionPane.showMessageDialog(
                                this,
                                String.format(
                                        Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_MESSAGE_ORDER_CREATE),
                                        selectedOrder.getId()
                                ),
                                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_INFO),
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        modifyOrder(selectedOrder, ModifyType.EDIT);
                    }
                }
        );
        userActionPanel.add(createOrder);

        editOrder = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_ORDER_EDIT),
                event -> {
                    Order selectedOrder = orderTableModel.getOrderByRow(orderTable.getSelectedRow());
                    if (selectedOrder != null) {
                        modifyOrder(selectedOrder, ModifyType.EDIT);
                    }
                }
        );
        userActionPanel.add(editOrder);

        cancelOrder = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_ORDER_CANCEL),
                event -> {
                    Order selectedOrder = orderTableModel.getOrderByRow(orderTable.getSelectedRow());
                    if (selectedOrder != null) {
                        selectedOrder.setState(State.CANCELLED);
                        OrderDao.updateOrder(selectedOrder);
                        orderTableModel.fireTableDataChanged();
                    }
                }
        );
        userActionPanel.add(cancelOrder);
    }

    public void reloadBundle() {
        cancelOrder.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_ORDER_CANCEL));
        createOrder.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_ORDER_CREATE));
        detailOrder.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_ORDER_DETAIL));
        editOrder.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_ORDER_EDIT));
        orderTable.createDefaultColumnsFromModel();
    }

    public void setHistoryMode(boolean isHistory) {
        orderTableModel.setHistory(isHistory);
        cancelOrder.setVisible(!isHistory);
        createOrder.setVisible(!isHistory);
        editOrder.setVisible(!isHistory);
    }

    public void setUser(User user) {
        currentUser = user;
    }

    private Order createNewOrder() {
        return Order.builder()
                .dateOn(new Date(Instant.now().toEpochMilli()))
                .executor(currentUser.getLogin())
                .state(State.IN_PROGRESS)
                .tableNumber(0)
                .updatedBy(currentUser.getLogin())
                .updatedOn(new Date(Instant.now().toEpochMilli()))
                .build();
    }

    private void modifyOrder(Order order, ModifyType modifyType) {
        OrderModifyForm.getInstance().setModifyType(modifyType);
        OrderModifyForm.getInstance().setUser(currentUser);
        OrderModifyForm.getInstance().setOrder(order);
        OrderModifyForm.getInstance().setVisible(true);
        MainForm.getInstance().setVisible(false);
    }
}
