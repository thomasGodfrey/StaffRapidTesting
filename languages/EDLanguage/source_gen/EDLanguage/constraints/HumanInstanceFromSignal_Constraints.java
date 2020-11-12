package EDLanguage.constraints;

/*Generated by MPS */

import jetbrains.mps.smodel.runtime.base.BaseConstraintsDescriptor;
import jetbrains.mps.smodel.runtime.ConstraintFunction;
import jetbrains.mps.smodel.runtime.ConstraintContext_CanBeChild;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import jetbrains.mps.smodel.runtime.CheckingNodeContext;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import jetbrains.mps.lang.smodel.generator.smodelAdapter.SNodeOperations;
import jetbrains.mps.lang.smodel.generator.smodelAdapter.SPropertyOperations;
import jetbrains.mps.lang.smodel.generator.smodelAdapter.SLinkOperations;
import jetbrains.mps.smodel.SNodePointer;
import org.jetbrains.mps.openapi.language.SConcept;
import jetbrains.mps.smodel.adapter.structure.MetaAdapterFactory;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.language.SProperty;

public class HumanInstanceFromSignal_Constraints extends BaseConstraintsDescriptor {
  public HumanInstanceFromSignal_Constraints() {
    super(CONCEPTS.HumanInstanceFromSignal$n7);
  }

  @Override
  protected ConstraintFunction<ConstraintContext_CanBeChild, Boolean> calculateCanBeChildConstraint() {
    return new ConstraintFunction<ConstraintContext_CanBeChild, Boolean>() {
      @NotNull
      public Boolean invoke(@NotNull ConstraintContext_CanBeChild context, @Nullable CheckingNodeContext checkingNodeContext) {
        boolean result = staticCanBeAChild(context.getNode(), context.getParentNode(), context.getConcept(), context.getLink());

        if (!(result) && checkingNodeContext != null) {
          checkingNodeContext.setBreakingNode(canBeChildBreakingPoint);
        }

        return result;
      }
    };
  }
  private static boolean staticCanBeAChild(SNode node, SNode parentNode, SAbstractConcept childConcept, SContainmentLink link) {
    {
      final SNode di = parentNode;
      if (SNodeOperations.isInstanceOf(di, CONCEPTS.DataInstanceMap$Ya)) {
        if (SPropertyOperations.getEnum(SLinkOperations.getTarget(di, LINKS.dataLine$x$TU), PROPS.valueType$F2s2).getPresentation().equals("person")) {
          return true;
        }
        if (SPropertyOperations.getEnum(SLinkOperations.getTarget(di, LINKS.dataLine$x$TU), PROPS.valueType$F2s2).getPresentation().equals("object")) {
          return true;
        }
        return false;
      }
    }
    return true;
  }
  private static final SNodePointer canBeChildBreakingPoint = new SNodePointer("r:8af044a8-b97e-4b60-8dc9-53a519dacbc1(EDLanguage.constraints)", "2480088909319835126");

  private static final class CONCEPTS {
    /*package*/ static final SConcept HumanInstanceFromSignal$n7 = MetaAdapterFactory.getConcept(0x7dcff301ba01414eL, 0x8574a8f6da31876bL, 0x7606d63a99baabefL, "EDLanguage.structure.HumanInstanceFromSignal");
    /*package*/ static final SConcept DataInstanceMap$Ya = MetaAdapterFactory.getConcept(0x7dcff301ba01414eL, 0x8574a8f6da31876bL, 0x45056e1fe037ad68L, "EDLanguage.structure.DataInstanceMap");
  }

  private static final class LINKS {
    /*package*/ static final SReferenceLink dataLine$x$TU = MetaAdapterFactory.getReferenceLink(0x7dcff301ba01414eL, 0x8574a8f6da31876bL, 0x45056e1fe037ad68L, 0x50f1fbdc6daee00dL, "dataLine");
  }

  private static final class PROPS {
    /*package*/ static final SProperty valueType$F2s2 = MetaAdapterFactory.getProperty(0x7dcff301ba01414eL, 0x8574a8f6da31876bL, 0x3a7166f1d96f0c96L, 0x3a7166f1d9709f4eL, "valueType");
  }
}
