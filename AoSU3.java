import java.util.Scanner;

class Hero {
    String name;
    int hp;
    int attkType;
    int attkDmg;
    int phyDef;
    int magDef;

    Hero(String name, int hp, int attkType, int attkDmg, int phyDef, int magDef) {
        this.name = name;
        this.hp = hp;
        this.attkType = attkType;
        this.attkDmg = attkDmg;
        this.phyDef = phyDef;
        this.magDef = magDef;
        checkAndWarn();
    }

    boolean checkAndWarn() {
        if (hp < 0 || attkDmg < 0 || phyDef < 0 || magDef < 0) {
            System.out.println("warning: value cannot be negative");
            return false;
        }
        return true;
    }

    void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("HP: " + hp);
        String attkTypeName = (attkType == 1) ? "physical" : "magical";
        System.out.println("Attack: " + attkDmg + " (" + attkTypeName + ")");
        System.out.println("Defense: " + phyDef + " (physical), " + magDef + " (magical)");
        checkAndWarn();
    }
}

class Team {
    Hero[] members;

    Team(Hero[] arr) {
        if (!is5MemberTeam(arr)) return;
        if (!isValid(arr)) return;
        members = new Hero[arr.length];
        for (int i = 0; i < arr.length; i++) {
            members[i] = arr[i];
        }
        isBalanced(arr);
    }

    boolean is5MemberTeam(Hero[] arr) {
        if (arr == null || arr.length < 5) {
            System.out.println("member is missing");
            return false;
        } else if (arr.length > 5) {
            System.out.println("too many members");
            return false;
        } else {
            System.out.println("full team");
            return true;
        }
    }

    boolean isValid(Hero[] arr) {
        if (arr == null || arr.length != 5) return false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].name.equals(arr[j].name)) {
                    System.out.println("cannot select same hero: " + arr[i].name);
                    return false;
                }
            }
        }
        System.out.println("valid hero selection");
        return true;
    }

    boolean isBalanced(Hero[] arr) {
        if (arr == null || arr.length != 5) return false;
        boolean hasCarry = false, hasFighter = false, hasMage = false, hasTank = false;
        for (Hero h : arr) {
            if (h instanceof Carry) hasCarry = true;
            else if (h instanceof Fighter) hasFighter = true;
            else if (h instanceof Mage) hasMage = true;
            else if (h instanceof Tank) hasTank = true;
        }
        if (hasCarry && hasFighter && hasMage && hasTank) {
            System.out.println("balanced team");
            return true;
        }
        String msg = "team needs";
        if (!hasCarry) msg += " carry";
        if (!hasFighter) msg += " fighter";
        if (!hasMage) msg += " mage";
        if (!hasTank) msg += " tank";
        System.out.println(msg);
        return false;
    }
}

class Carry extends Hero {
    Carry(String name, int hp, int attkType, int attkDmg, int phyDef, int magDef) {
        super(name, hp, attkType, attkDmg, phyDef, magDef);
    }
}

class Fighter extends Hero {
    Fighter(String name, int hp, int attkType, int attkDmg, int phyDef, int magDef) {
        super(name, hp, attkType, attkDmg, phyDef, magDef);
    }
}

class Mage extends Hero {
    Mage(String name, int hp, int attkType, int attkDmg, int phyDef, int magDef) {
        super(name, hp, attkType, attkDmg, phyDef, magDef);
    }
}

class Tank extends Hero {
    Tank(String name, int hp, int attkType, int attkDmg, int phyDef, int magDef) {
        super(name, hp, attkType, attkDmg, phyDef, magDef);
    }
}

class Support extends Hero {
    Support(String name, int hp, int attkType, int attkDmg, int phyDef, int magDef) {
        super(name, hp, attkType, attkDmg, phyDef, magDef);
    }
}

class Assassin extends Hero {
    Assassin(String name, int hp, int attkType, int attkDmg, int phyDef, int magDef) {
        super(name, hp, attkType, attkDmg, phyDef, magDef);
    }
}

public class AoSU3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        Hero[] members = null;
        if (N > 0) {
            members = new Hero[N];
            for (int i = 0; i < N; ++i) {
                int type = scan.nextInt();
                String name = scan.next();
                int hp = scan.nextInt();
                int attkType = scan.nextInt();
                int attkDmg = scan.nextInt();
                int phyDef = scan.nextInt();
                int magDef = scan.nextInt();
                if (type == 1)
                    members[i] = new Carry(name, hp, attkType, attkDmg, phyDef, magDef);
                else if (type == 2)
                    members[i] = new Fighter(name, hp, attkType, attkDmg, phyDef, magDef);
                else if (type == 3)
                    members[i] = new Mage(name, hp, attkType, attkDmg, phyDef, magDef);
                else if (type == 4)
                    members[i] = new Tank(name, hp, attkType, attkDmg, phyDef, magDef);
                else if (type == 5)
                    members[i] = new Support(name, hp, attkType, attkDmg, phyDef, magDef);
                else
                    members[i] = new Assassin(name, hp, attkType, attkDmg, phyDef, magDef);
            }
        }
        Team t = new Team(members);
        System.out.println(t.is5MemberTeam(members));
        System.out.println(t.isValid(members));
        System.out.println(t.isBalanced(members));
    }
}