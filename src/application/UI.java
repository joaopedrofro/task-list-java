package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import model.entities.Task;
import model.entities.TaskCreationException;
import model.entities.TaskException;
import model.entities.TaskManager;

public final class UI {
	
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static Scanner sc = new Scanner(System.in);
	
	private static void header() throws IOException {
		System.out.println("\n".repeat(50));
		System.out.println("LISTA DE TAREFAS");
		System.out.println("Data atual: " + LocalDate.now().format(dateFormatter));
	}
	
	public static void mainMenu(TaskManager taskManager) throws IOException {
		boolean continueExecuting = true;
		
		while (continueExecuting) {
			header();
			printTasks(taskManager);
			
			System.out.println("\nOPÇÕES:");
			System.out.println("1 - Criar tarefa");
			System.out.println("2 - Concluir tarefa");
			System.out.println("3 - Remover tarefa");
			System.out.println("4 - Renomear tarefa");
			System.out.println("5 - Desmarcar tarefa");
			System.out.println("6 - Sair");
			
			System.out.print("\nInforme a opção: ");
			try {
				int id, menuOption = Integer.parseInt(sc.nextLine());
			
				switch (menuOption) {
					case 1:
						header();
						System.out.println("\nCRIAÇÃO DE TAREFA");
						System.out.println("\nID: " + (taskManager.getNumberOfCreatedTasks() + 1));
						System.out.print("Título da tarefa: ");
						String taskTitle = sc.nextLine();
						taskManager.createTask(taskTitle);
						break;
					case 2:
						if (taskManager.getUncompletedTasks().stream().count() == 0L) {
							throw new TaskException("Não existem tarefas para concluir!");
						}
						header();
						System.out.println();
						taskManager.getUncompletedTasks().forEach(System.out::println);
						System.out.println("\nCONCLUIR TAREFA");
						System.out.print("\nID da tarefa: ");
						id = Integer.parseInt(sc.nextLine());
						taskManager.completeTask(id);
						break;
					case 3:
						if (taskManager.getNumberOfCreatedTasks() == 0) {
							throw new TaskException("Ainda não existem tarefas criadas!");
						}
						header();
						printTasks(taskManager);
						System.out.println("\nREMOÇÃO DE TAREFA");
						System.out.print("\nID da tarefa: ");
						id = Integer.parseInt(sc.nextLine());
						taskManager.deleteTask(id);
						break;
					case 4:
						if (taskManager.getNumberOfCreatedTasks() == 0) {
							throw new TaskException("Ainda não existem tarefas criadas!");
						}
						header();
						printTasks(taskManager);
						System.out.println("\nRENOMEAR DE TAREFA");
						System.out.print("\nID da tarefa: ");
						id = Integer.parseInt(sc.nextLine());
						if (!taskManager.taskExists(id)) {
							throw new TaskException("ID Inválido!");
						}
						System.out.print("Novo título da tarefa: ");
						String newTitle = sc.nextLine();
						taskManager.renameTask(id, newTitle);
						break;
					case 5:
						if (taskManager.getCompletedTasks().stream().count() == 0L) {
							throw new TaskException("Não existem tarefas para desmarcar!");
						}
						header();
						System.out.println();
						taskManager.getCompletedTasks().forEach(System.out::println);
						System.out.println("\nDESMARCAR TAREFA");
						System.out.print("\nID da tarefa: ");
						id = Integer.parseInt(sc.nextLine());
						taskManager.uncompleteTask(id);
						break;
					case 6:
						continueExecuting = false;
						break;
				}
			} catch (TaskCreationException e) {
				System.out.println("\nErro! " + e.getMessage());
				System.out.print("\nPressione enter para continuar...");
				sc.nextLine();
			} catch (TaskException e) {
				System.out.println("\nErro! " + e.getMessage());
				System.out.print("\nPressione enter para continuar...");
				sc.nextLine();
			} catch (NumberFormatException e) {
				System.out.println("\nOpção inválida!");
				System.out.print("\nPressione enter para continuar...");
				sc.nextLine();
			}
		}
		sc.close();
	}
	
	private static void printTasks(TaskManager taskManager) {
		if (!taskManager.getAllTasks().isEmpty()) {
			List<Task> uncompletedTasks = taskManager.getUncompletedTasks();
			if (!uncompletedTasks.isEmpty()) {
				System.out.println("\nA FAZER:");
				uncompletedTasks.forEach(System.out::println);
			}
			
			List<Task> completedTasks = taskManager.getCompletedTasks();
			if (!completedTasks.isEmpty()) {
				System.out.println("\nCONCLUÍDAS:");
				completedTasks.forEach(System.out::println);
			}
		}
	}
	
}
