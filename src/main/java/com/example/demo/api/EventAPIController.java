package com.example.demo.api;

import com.example.demo.Metier.Services.EventService;
import com.example.demo.Metier.entities.Event;
import com.example.demo.converters.DateConverter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/event")
public class EventAPIController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        EventService eventService = new EventService();
        List<Object[]> eventsData = eventService.findAllEvent(); // Assuming findAllEvent() returns a List<Object[]>

// Create a JSON array to hold the events
        JsonArray jsonArray = new JsonArray();

        for (Object[] eventData : eventsData) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", (Long) eventData[0]); // Assuming id is Long
            jsonObject.addProperty("title", (String) eventData[1]); // Assuming title is String
            jsonObject.addProperty("start", (String) eventData[2]); // Assuming start is String
            jsonObject.addProperty("end", (String) eventData[3]); // Assuming end is String
            // Add more properties as needed
            jsonArray.add(jsonObject);
        }
        // Write the JSON array to the response
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.print(gson.toJson(jsonArray));
        writer.flush();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
