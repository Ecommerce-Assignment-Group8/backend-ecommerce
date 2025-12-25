package com.example.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueStatsDTO {
    private Integer totalRevenue;
    private List<Map<String, Object>> chartData;
}

