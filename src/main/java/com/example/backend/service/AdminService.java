package com.example.backend.service;

import com.example.backend.dto.response.RevenueStatsDTO;
import com.example.backend.entity.Order;
import com.example.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {
    @Autowired
    private OrderRepository orderRepository;

    public ResponseEntity<RevenueStatsDTO> getRevenueStats(Integer year) {
        List<Order> allOrders = orderRepository.findAll();
        
        int totalRevenue = 0;
        Map<String, Integer> monthlyRevenue = new LinkedHashMap<>();
        
        for (int month = 1; month <= 12; month++) {
            String monthKey = String.format("%04d-%02d", year, month);
            monthlyRevenue.put(monthKey, 0);
        }
        
        Calendar cal = Calendar.getInstance();
        for (Order order : allOrders) {
            if (order.getStatus() == Order.Status.FINISHED && order.getTotalPrice() != null) {
                if (order.getOrderDate() != null) {
                    cal.setTime(order.getOrderDate());
                    int orderYear = cal.get(Calendar.YEAR);
                    int orderMonth = cal.get(Calendar.MONTH) + 1;
                    
                    if (orderYear == year) {
                        totalRevenue += order.getTotalPrice();
                        String monthKey = String.format("%04d-%02d", orderYear, orderMonth);
                        monthlyRevenue.put(monthKey, monthlyRevenue.getOrDefault(monthKey, 0) + order.getTotalPrice());
                    }
                }
            }
        }
        
        List<Map<String, Object>> chartData = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            String monthKey = String.format("%04d-%02d", year, month);
            Map<String, Object> dataPoint = new HashMap<>();
            String monthName = getMonthName(month);
            dataPoint.put("month", monthName);
            dataPoint.put("revenue", monthlyRevenue.getOrDefault(monthKey, 0));
            chartData.add(dataPoint);
        }
        
        RevenueStatsDTO stats = new RevenueStatsDTO();
        stats.setTotalRevenue(totalRevenue);
        stats.setChartData(chartData);
        
        return ResponseEntity.ok(stats);
    }
    
    private String getMonthName(int month) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return months[month - 1];
    }
}

