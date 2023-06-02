package com.epam.mjc;

public class MethodParser {

    public MethodSignature parseFunction(String signatureString) {
        MethodSignature methodSignature = new MethodSignature(null);

        String method = signatureString.substring(0, signatureString.indexOf("("));
        String arguments = signatureString.substring(signatureString.indexOf("(") + 1, signatureString.indexOf(")")).trim();

        String[] mparts = method.split(" ");
        String[] aparts = arguments.split(" |, ");

        for (int i = 0; i < mparts.length; i++) {
            if (mparts[i].equals("public") || mparts[i].equals("private")) {
                methodSignature.setAccessModifier(mparts[i]);
            } else if (mparts[i].equals("void") || mparts[i].equals("String") || mparts[i].equals("float")) {
                methodSignature.setReturnType(mparts[i]);
            } else {
                methodSignature.setMethodName(mparts[i]);
            }
        }
        if (aparts.length < 2) {
            methodSignature.getArguments();
        } else {
            for (int i = 0; i < aparts.length; i += 2) {
                String argumentType = aparts[i].trim();
                String argumentName = aparts[i + 1].trim();
                MethodSignature.Argument argument = new MethodSignature.Argument(argumentType, argumentName);
                methodSignature.getArguments().add(argument);
            }
        }
        return methodSignature;
    }
}
