package com.example.demo_jdk8.stream;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Data;

public class Streams {
	
	private enum Status {
		OPEN, CLOSED
	};
	
	@Data
	private static final class Task {
		private final Status status;
		private final Integer points;
		
		Task(final Status status, final Integer points) {
			this.status = status;
			this.points = points;
		}

		@Override
		public String toString() {
			return String.format("[%s, %d]", status, points);
		}
		
	}
	
	public static void main(String[] args) {
		final Collection<Task> tasks = new ArrayList<>();
		tasks.add(new Task(Status.OPEN, 5));
		tasks.add(new Task(Status.OPEN, 13));
		tasks.add(new Task(Status.CLOSED, 8));
		
		final long totalPointsOfOpenTasks = tasks.stream().filter(task -> task.getStatus() == Status.OPEN).mapToInt(Task :: getPoints).sum();
		System.out.println( "Total points: " + totalPointsOfOpenTasks );
	}
	
}
