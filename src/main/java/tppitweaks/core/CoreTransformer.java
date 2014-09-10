package tppitweaks.core;

import static org.objectweb.asm.Opcodes.*;

import java.util.Iterator;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import tppitweaks.TPPITweaks;

public class CoreTransformer implements IClassTransformer
{
	private final String FURNACE_GEN_CLASS = "extrautils.tileentity.generator.TileEntityGeneratorFurnace";
	private final String SURVIVALIST_GEN_CLASS = "extrautils.tileentity.generator.TileEntityGeneratorFurnaceSurvival";
	private final String KEYSTONE_CONTAINER_CLASS = "am2.containers.ContainerKeystoneLockable";

	private final String FURNACE_GEN_METHOD = "getFuelBurn";
	private final String FURNACE_GEN_METHOD_DESC = "(Lnet/minecraft/item/ItemStack;)I";

	private final String NEW_FURNACE_GEN_METHOD = "getFuelBurnFurnace";
	private final String NEW_SURVIVALIST_GEN_METHOD = "getFuelBurnSurvivalist";

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass)
	{
		if (name.compareTo(FURNACE_GEN_CLASS) == 0)
			return transformFurnaceClass(basicClass, NEW_FURNACE_GEN_METHOD);
		else if (name.compareTo(SURVIVALIST_GEN_CLASS) == 0)
			return transformFurnaceClass(basicClass, NEW_SURVIVALIST_GEN_METHOD);
		else if (name.compareTo(KEYSTONE_CONTAINER_CLASS) == 0)
			return transformKeystoneContainer(basicClass);
		return basicClass;
	}

	private byte[] transformFurnaceClass(byte[] basicClass, String newMethod)
	{
		TPPITweaks.logger.info("Transforming ExU Generator to method " + newMethod);
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(basicClass);
		classReader.accept(classNode, 0);

		Iterator<MethodNode> methods = classNode.methods.iterator();
		while (methods.hasNext())
		{
			MethodNode m = methods.next();
			if (m.name.equals(FURNACE_GEN_METHOD))
			{
				for (int index = 0; index < m.instructions.size(); index++)
				{
					if (m.instructions.get(index).getType() == AbstractInsnNode.METHOD_INSN)
					{
						LabelNode lmm1Node = new LabelNode(new Label());

						LabelNode jumpLabel = new LabelNode(new Label());

						InsnList toInject = new InsnList();

						toInject.add(new VarInsnNode(ALOAD, 1));

						toInject.add(new MethodInsnNode(INVOKESTATIC, "tppitweaks/core/CoreMethods", newMethod, FURNACE_GEN_METHOD_DESC));

						toInject.add(jumpLabel);
						toInject.add(lmm1Node);

						m.instructions.insert(m.instructions.get(index), toInject);

						break;
					}
				}
			}
		}

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		classNode.accept(cw);

		return cw.toByteArray();
	}

	private byte[] transformKeystoneContainer(byte[] basicClass)
	{
		TPPITweaks.logger.info("Transforming AM2 keystone container...");
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(basicClass);
		classReader.accept(classNode, 0);

		Iterator<MethodNode> methods = classNode.methods.iterator();
		while (methods.hasNext())
		{
			MethodNode m = methods.next();
			if (m.name.equals("transferStackInSlot"))
			{
				m.instructions.clear();

				m.instructions.add(new InsnNode(ACONST_NULL)); // forget it
				m.instructions.add(new InsnNode(ARETURN));

				break;
			}
		}

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		classNode.accept(cw);
		return cw.toByteArray();
	}
}
