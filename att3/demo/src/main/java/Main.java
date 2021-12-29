import ru.vsu.cs.raspopov.MyHashSet;
import ru.vsu.cs.raspopov.MyMultiHashSet;

import java.lang.reflect.Method;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        int typeOfSet = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("If u want work with HashSet - type 1 \n" +
                            "If u want work with MultiHashSet - type 2");
        typeOfSet = Integer.parseInt(sc.nextLine());
        System.out.println("Enter type that u want to work (Integer or String):");
        String setType = sc.nextLine();
        Class<?> clz;
        Object set;
        if (typeOfSet == 1) {
            clz = MyHashSet.class;
            set = new MyHashSet<>();
        } else if (typeOfSet == 2) {
            clz = MyMultiHashSet.class;
            set = new MyMultiHashSet<>();
        } else {
            throw new Exception("Unexpected type of class");
        }
        start(sc, setType, clz, set);
    }

    public static void start(Scanner sc, String setTypes, Class<?> clz, Object set) throws Exception {
        System.out.println("Type name of method, that u want to use or break if exit");
        String nameMethod = sc.nextLine();
        if (nameMethod.equals("break")) {
            return;
        }
        Class<?> setType = returnTypeOfSet(setTypes);
        List<Method> methods = Arrays.stream(clz.getMethods())
                .filter(method -> method.getName().equals(nameMethod))
                .toList();
        if (methods.size() == 0) {
            System.err.println("Wrong method, try again");
            start(sc, setTypes,clz, set);
        }
        int methodSize = 0;
        if (methods.size() > 1) {
            System.out.println("What method u want to use with(type 0)" + methods.get(0).getParameterCount() +
                    " parameters or " + methods.get(1).getParameterCount() + " parameters(type 1)");
            methodSize = Integer.parseInt(sc.nextLine());
        }
        Method method = methods.get(methodSize);
        int parametersCount = method.getParameterCount();
        if (parametersCount > 0) {
            Class[] clzs = method.getParameterTypes();
            Object[] parameters = new Object[parametersCount];
            System.out.println("Type parameters");
            int[] counter = new int[]{ 1 };
            Arrays.stream(clzs).forEach(parameter -> {
                System.out.println(counter[0] + ", type of parameter - " + clzs[counter[0] - 1].getName() + ":");
                if (counter[0] == 2) {
                    parameters[counter[0] - 1] = Integer.parseInt(sc.nextLine());
                }
                if (setType.equals(String.class) && counter[0] == 1) {
                    parameters[counter[0] - 1] = sc.nextLine();
                }
                if (setType.equals(Integer.class) && counter[0] == 1) {
                    parameters[counter[0] - 1] = Integer.parseInt(sc.nextLine());
                }
                counter[0]++;
            });
            System.out.println("Method return: " + method.invoke(set, parameters));
        } else {
            System.out.println("Method return: " + method.invoke(set));
        }
        start(sc, setTypes, clz, set);
    }

    public static Class<?> returnTypeOfSet(String name) throws Exception {
        if (name.equals("Integer")) {
            return Integer.class;
        }
        if (name.equals("String")) {
            return String.class;
        }
        throw new Exception("Not supported class to work");
    }
}
