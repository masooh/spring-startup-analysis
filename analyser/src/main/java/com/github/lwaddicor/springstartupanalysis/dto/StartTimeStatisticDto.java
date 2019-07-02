package com.github.lwaddicor.springstartupanalysis.dto;

/**
 * Dto which represents the start time statistic for a bean
 */
public class StartTimeStatisticDto implements Comparable<StartTimeStatisticDto> {

    private String name;
    private Long time;

    /**
     * Constructor
     *
     * @param name The name of the bean
     * @param deltaT The time taken for it to start
     */
    public StartTimeStatisticDto(String name, long deltaT) {
        this.name = name;
        this.time = deltaT;
    }

    /**
     * @return Gets the time taken to start
     */
    public Long getTime() {
        return time;
    }

    /**
     * @return Gets the name of the bean starting
     */
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(StartTimeStatisticDto other) {
        int timeComparison = this.time.compareTo(other.time) * -1;
        return timeComparison != 0 ? timeComparison : this.name.compareTo(other.name);
    }
}
