package com.github.lwaddicor.springstartupanalysis.web;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.lwaddicor.springstartupanalysis.StartProgressBeanPostProcessor;
import com.github.lwaddicor.springstartupanalysis.dto.Data;
import com.github.lwaddicor.springstartupanalysis.dto.StartTimeStatisticDto;

/**
 * This controller creates the endpoints which allow the display of the
 * graphic representing the spring context start time.
 */
@Controller
@RequestMapping(value = "/spring-startup")
public class SpringStartupController {

    private Data data = new Data();

    @Autowired
    private StartProgressBeanPostProcessor processor;

    private void doConstruct() {
        Collections.sort(processor.getTimesAsArray());
        List<StartTimeStatisticDto> times = processor.getTimesAsArray()
                .subList(0, (processor.getTimesAsArray().size() > 20) ? 20 : (processor.getTimesAsArray().size() - 1));

        // shorten EnhancerBySpringCGLIB$$9e6123a7
        data.setLabels(times.stream().map(startTimeStatisticDto -> startTimeStatisticDto.getName().replaceFirst("\\$\\$.*$", "")).collect(Collectors.toList()));

        Data.Dataset dataset = new Data.Dataset();
        dataset.setData(times.stream().map(StartTimeStatisticDto::getTime).collect(Collectors.toList()));
        dataset.setBackgroundColor(
                Arrays.asList("red", "gray", "green", "aqua", "blue", "lime", "fuchsia",
                        "red", "gray", "green", "aqua", "blue", "lime", "fuchsia",
                        "red", "gray", "green", "aqua", "blue", "lime", "fuchsia",
                        "red", "gray", "green", "aqua", "blue", "lime", "fuchsia",
                        "red", "gray", "green", "aqua", "blue", "lime", "fuchsia",
                        "red", "gray", "green", "aqua", "blue", "lime", "fuchsia"
                )
        );
        data.setDatasets(Collections.singletonList(dataset));
    }

    /**
     * Gets the html content for the page.
     *
     * @return The html content
     */
    @GetMapping(value = "/", produces = "text/html;charset=UTF-8")
    public String getHtml() {
        doConstruct();
        return "data";
    }

    @GetMapping(value = "/data.json", produces = "application/json")
    @ResponseBody
    public Data generateJson(HttpServletResponse response) {
        return data;
    }
}