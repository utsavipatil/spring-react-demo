package com.utsavi.spring_react_demo.assignment;

import java.time.LocalTime;
import java.util.Map;

public record RevenueReport(LocalTime time, Map<String,Integer> revenue){}
