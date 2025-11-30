package edu.lewis.fitness_center;

import java.util.*;

public class Main {

	// "Global" data structures ---------------------------------------------

    static List<Person> members = new ArrayList<>();
    // member: { id (Integer), name (String), membership_type (String), active (Boolean), balance (Double) }

    static List<FitnessClass> fitnessClasses = new ArrayList<>();
    // class: { id (Integer), name (String), difficulty (String), capacity (Integer), enrolled_ids (List<Integer>) }

    static List<Person> trainers = new ArrayList<>();
    // trainer: { id (Integer), name (String), specialty (String), schedule (List<String>) }

    static int nextMemberId = 1;
    static int nextClassId = 1;
    static int nextTrainerId = 1;
    
    private static BetterScanner sc = new BetterScanner();

    // Member-related functions ----------------------------------------------

    public static void addMember() {
        System.out.println("\n=== Add New Member ===");
        String name = sc.readNonempty("Name: ");
        System.out.println("Membership types: student, faculty, community");
        System.out.print("Membership type: ");
        MembershipType membershipType = MembershipType.COMMUNITY;;
        String str = sc.nextLine().trim().toLowerCase();
        if (str.equals(MembershipType.STUDENT.name))
        {
        	membershipType = MembershipType.STUDENT;
        }
        else if(str.equals(MembershipType.FACULTY.name))
        {
        	membershipType = MembershipType.FACULTY;
        }

        Member member = new Member(nextMemberId, name, membershipType, true, 0.0);

        members.add(member);
        System.out.println("Member added with id " + nextMemberId);
        nextMemberId++;
    }

    public static void listMembers(boolean showDetails) {
        System.out.println("\n=== Members ===");
        if (members.isEmpty()) {
            System.out.println("No members found.");
        }
        for (Person p : members) {
        	Member m = (Member)p;
            int id = m.getId();
            String name = m.getName();
            if (showDetails) {
            	MembershipType membershipType = m.getMembershipType();
                boolean active = m.isActive();
                double balance = m.getBalance();
                String status = active ? "Active" : "Inactive";
                System.out.printf("[%d] %s (%s, %s) Balance: $%.2f%n",
                        id, name, membershipType.name, status, balance);
            } else {
                System.out.printf("[%d] %s%n", id, name);
            }
        }
        System.out.println();
    }

    public static Member findMemberById(int memberId) {
        for (Person p : members) {
        	Member m = (Member)p;
            int id = m.getId();
            if (id == memberId) {
                return m;
            }
        }
        return null;
    }

    public static void deactivateMember() {
        System.out.println("\n=== Deactivate Member ===");
        listMembers(false);
        int mid = sc.readInt("Enter member id to deactivate: ", null, null);
        Member member = findMemberById(mid);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }
        boolean active = member.isActive();
        if (!active) {
            System.out.println("Member is already inactive.");
            return;
        }
        member.setActive(false);
        System.out.println("Member " + member.getName() + " is now inactive.");
    }

    public static void addChargeToMember() {
        System.out.println("\n=== Add Charge to Member ===");
        listMembers(false);
        int mid = sc.readInt("Enter member id to charge: ", null, null);
        Member member = findMemberById(mid);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }
        System.out.print("Charge amount: $");
        String line = sc.nextLine().trim();
        double amount;
        try {
            amount = Double.parseDouble(line);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return;
        }
        double balance = member.getBalance();
        balance += amount;
        member.setBalance(balance);
        System.out.printf("Added $%.2f to %s's balance.%n", amount, member.getName());
    }

    public static void applyPaymentFromMember() {
        System.out.println("\n=== Record Payment ===");
        listMembers(false);
        int mid = sc.readInt("Enter member id: ", null, null);
        Member member = findMemberById(mid);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }
        System.out.print("Payment amount: $");
        String line = sc.nextLine().trim();
        double amount;
        try {
            amount = Double.parseDouble(line);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return;
        }
        double balance = member.getBalance();
        balance -= amount;
        member.setBalance(balance);
        System.out.printf("Recorded payment of $%.2f from %s.%n", amount, member.getName());
    }

    // Class-related functions -----------------------------------------------

    public static void createClass() {
        System.out.println("\n=== Create Fitness Class ===");
        String name = sc.readNonempty("Class name: ");
        System.out.print("Difficulty (beginner/intermediate/advanced): ");
        ClassDifficulty difficulty = ClassDifficulty.BEGINNER;
        String str = sc.nextLine().trim().toLowerCase();
        if (str.equals(ClassDifficulty.INTERMEDIATE.name))
        {
        	difficulty = ClassDifficulty.INTERMEDIATE;
        }
        else if(str.equals(ClassDifficulty.ADVANCED.name))
        {
        	difficulty = ClassDifficulty.ADVANCED;
        }
        int capacity = sc.readInt("Capacity: ", 1, null);

        FitnessClass fitnessClass = new FitnessClass(nextClassId, name, difficulty, capacity);

        fitnessClasses.add(fitnessClass);
        System.out.println("Fitness class created with id " + nextClassId);
        nextClassId++;
    }

    public static void listClasses(boolean showEnrollment) {
        System.out.println("\n=== Fitness Classes ===");
        if (fitnessClasses.isEmpty()) {
            System.out.println("No classes found.");
        }
        for (FitnessClass c : fitnessClasses) {
            int id = c.getId();
            String name = c.getName();
            if (showEnrollment) {
            	ClassDifficulty difficulty = c.getDifficulty();
                int capacity =  c.getCapacity();
                List<Integer> enrolledIds = c.getEnrolledIds();
                System.out.printf("[%d] %s (%s, capacity %d, enrolled %d)%n",
                        id, name, difficulty.name, capacity, enrolledIds.size());
            } else {
                System.out.printf("[%d] %s%n", id, name);
            }
        }
        System.out.println();
    }

    public static FitnessClass findClassById(int classId) {
        for (FitnessClass c : fitnessClasses) {
            int id = c.getId();
            if (id == classId) {
                return c;
            }
        }
        return null;
    }

    public static void enrollMemberInClass() {
        System.out.println("\n=== Enroll Member in Class ===");
        listMembers(false);
        int mid = sc.readInt("Enter member id: ", null, null);
        Member member = findMemberById(mid);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }
        boolean active = member.isActive();
        if (!active) {
            System.out.println("Cannot enroll an inactive member.");
            return;
        }

        listClasses(false);
        int cid = sc.readInt("Enter class id: ", null, null);
        FitnessClass fclass = findClassById(cid);
        if (fclass == null) {
            System.out.println("Class not found.");
            return;
        }

        int capacity = fclass.getCapacity();
        @SuppressWarnings("unchecked")
        List<Integer> enrolledIds = fclass.getEnrolledIds();

        if (enrolledIds.size() >= capacity) {
            System.out.println("Class is full.");
            return;
        }

        if (enrolledIds.contains(mid)) {
            System.out.println("Member is already enrolled in this class.");
            return;
        }

        enrolledIds.add(mid);
        System.out.println("Enrolled " + member.getName() + " in " + fclass.getName() + ".");

        // Add charge with manual discount rules
        double baseFee = 10.0;
        MembershipType membershipType = member.getMembershipType();
        double discountedFee = baseFee * membershipType.discountMultiplier;

        double balance = member.getBalance();
        balance += discountedFee;
        member.setBalance(balance);

        System.out.printf(
                "Charged $%.2f for class (base $%.2f, type: %s).%n",
                discountedFee, baseFee, membershipType.name
        );
    }

    public static void listClassRoster() {
        System.out.println("\n=== Class Roster ===");
        listClasses(false);
        int cid = sc.readInt("Enter class id: ", null, null);
        FitnessClass fclass = findClassById(cid);
        if (fclass == null) {
            System.out.println("Class not found.");
            return;
        }

        System.out.println("\nRoster for " + fclass.getName() + ":");
        List<Integer> enrolledIds = fclass.getEnrolledIds();
        if (enrolledIds.isEmpty()) {
            System.out.println("No members enrolled.");
            return;
        }

        for (int mid : enrolledIds) {
            Member m = findMemberById(mid);
            if (m != null) {
                System.out.println("- " + m.getName() + " (" + m.getMembershipType().name + ")");
            }
        }
    }

    // Trainer-related functions ---------------------------------------------

    public static void addTrainer() {
        System.out.println("\n=== Add Trainer ===");
        String name = sc.readNonempty("Trainer name: ");
        String specialty = sc.readNonempty("Specialty (e.g., yoga, strength, cardio): ");
        List<String> schedule = new ArrayList<>();

        System.out.println("Enter trainer availability (e.g., Mon 9-11). Leave blank to stop.");
        while (true) {
            System.out.print("Availability: ");
            String slot = sc.nextLine().trim();
            if (slot.isEmpty()) {
                break;
            }
            schedule.add(slot);
        }

        Trainer trainer = new Trainer(nextTrainerId, name, specialty, schedule);

        trainers.add(trainer);
        System.out.println("Trainer added with id " + nextTrainerId);
        nextTrainerId++;
    }

    public static void listTrainers(boolean showSchedule) {
        System.out.println("\n=== Trainers ===");
        if (trainers.isEmpty()) {
            System.out.println("No trainers found.");
        }
        for (Person p : trainers) {
        	Trainer t = (Trainer)p;
            int id = t.getId();
            String name = t.getName();
            String specialty = t.getSpecialty();
            if (showSchedule) {
                List<String> schedule = t.getSchedule();
                String scheduleStr = (schedule == null || schedule.isEmpty())
                        ? "No availability"
                        : String.join(", ", schedule);
                System.out.printf("[%d] %s - %s (Schedule: %s)%n",
                        id, name, specialty, scheduleStr);
            } else {
                System.out.printf("[%d] %s%n", id, name);
            }
        }
        System.out.println();
    }

    public static Trainer findTrainerById(int trainerId) {
        for (Person p : trainers) {
        	Trainer t = (Trainer)p;
            int id = (Integer) t.getId();
            if (id == trainerId) {
                return t;
            }
        }
        return null;
    }

    public static void updateTrainerSchedule() {
        System.out.println("\n=== Update Trainer Schedule ===");
        listTrainers(false);
        int tid = sc.readInt("Enter trainer id: ", null, null);
        Trainer trainer = findTrainerById(tid);
        if (trainer == null) {
            System.out.println("Trainer not found.");
            return;
        }

        List<String> schedule = trainer.getSchedule();

        System.out.println("Current schedule:");
        for (String s : schedule) {
            System.out.println("- " + s);
        }

        System.out.println("1. Replace schedule");
        System.out.println("2. Add to schedule");
        int choice = sc.readInt("Choice: ", 1, 2);

        if (choice == 1) {
            schedule.clear();
            System.out.println("Enter new availability (blank to finish):");
            while (true) {
                System.out.print("Availability: ");
                String slot = sc.nextLine().trim();
                if (slot.isEmpty()) {
                    break;
                }
                schedule.add(slot);
            }
        } else {
            System.out.println("Enter additional availability (blank to finish):");
            while (true) {
                System.out.print("Availability: ");
                String slot = sc.nextLine().trim();
                if (slot.isEmpty()) {
                    break;
                }
                schedule.add(slot);
            }
        }

        System.out.println("Updated schedule:");
        for (String s : schedule) {
            System.out.println("- " + s);
        }
    }

    // Reporting / summaries -------------------------------------------------

    public static void showSummaryReport() {
        System.out.println("\n=== Summary Report ===");
        int totalMembers = members.size();
        int activeMembers = 0;
        double totalBalance = 0.0;

        for (Person p : members) {
        	Member m = (Member)p;
            if (m.isActive()) {
                activeMembers++;
            }
            totalBalance += m.getBalance();
        }

        System.out.println("Total members: " + totalMembers);
        System.out.println("Active members: " + activeMembers);
        System.out.printf("Total outstanding balance: $%.2f%n", totalBalance);

        System.out.println("\nClasses:");
        for (FitnessClass c : fitnessClasses) {
            String name = c.getName();
            ClassDifficulty difficulty = c.getDifficulty();
            int capacity = c.getCapacity();
            List<Integer> enrolledIds = c.getEnrolledIds();
            System.out.printf("- %s (%s): %d/%d enrolled%n",
                    name, difficulty, enrolledIds.size(), capacity);
        }

        System.out.println("\nTrainers:");
        for (Person p : trainers) {
        	Trainer t = (Trainer)p;
            System.out.printf("- %s (%s)%n", t.getName(), t.getSpecialty());
        }
        System.out.println();
    }

    // Menus -----------------------------------------------------------------

    public static void memberMenu() {
        while (true) {
            System.out.println("\n=== Member Menu ===");
            System.out.println("1. Add member");
            System.out.println("2. List members");
            System.out.println("3. Deactivate member");
            System.out.println("4. Add charge to member");
            System.out.println("5. Record payment from member");
            System.out.println("6. Back to main menu");
            int choice = sc.readInt("Choice: ", 1, 6);

            if (choice == 1) {
                addMember();
            } else if (choice == 2) {
                listMembers(true);
            } else if (choice == 3) {
                deactivateMember();
            } else if (choice == 4) {
                addChargeToMember();
            } else if (choice == 5) {
                applyPaymentFromMember();
            } else if (choice == 6) {
                break;
            }

            sc.pause();
        }
    }

    public static void classesMenu() {
        while (true) {
            System.out.println("\n=== Classes Menu ===");
            System.out.println("1. Create fitness class");
            System.out.println("2. List fitness classes");
            System.out.println("3. Enroll member in class");
            System.out.println("4. Show class roster");
            System.out.println("5. Back to main menu");
            int choice = sc.readInt("Choice: ", 1, 5);

            if (choice == 1) {
                createClass();
            } else if (choice == 2) {
                listClasses(true);
            } else if (choice == 3) {
                enrollMemberInClass();
            } else if (choice == 4) {
                listClassRoster();
            } else if (choice == 5) {
                break;
            }

            sc.pause();
        }
    }

    public static void trainerMenu() {
        while (true) {
            System.out.println("\n=== Trainer Menu ===");
            System.out.println("1. Add trainer");
            System.out.println("2. List trainers");
            System.out.println("3. Update trainer schedule");
            System.out.println("4. Back to main menu");
            int choice = sc.readInt("Choice: ", 1, 4);

            if (choice == 1) {
                addTrainer();
            } else if (choice == 2) {
                listTrainers(true);
            } else if (choice == 3) {
                updateTrainerSchedule();
            } else if (choice == 4) {
                break;
            }

            sc.pause();
        }
    }

    public static void mainMenu() {
        while (true) {
            System.out.println("\n=== Campus Fitness Center Management ===");
            System.out.println("1. Manage members");
            System.out.println("2. Manage classes");
            System.out.println("3. Manage trainers");
            System.out.println("4. Show summary report");
            System.out.println("5. Exit");
            int choice = sc.readInt("Choice: ", 1, 5);

            if (choice == 1) {
                memberMenu();
            } else if (choice == 2) {
                classesMenu();
            } else if (choice == 3) {
                trainerMenu();
            } else if (choice == 4) {
                showSummaryReport();
                sc.pause();
            } else if (choice == 5) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }

}
