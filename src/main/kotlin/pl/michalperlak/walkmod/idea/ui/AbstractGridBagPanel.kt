package pl.michalperlak.walkmod.idea.ui

import com.intellij.openapi.ui.LabeledComponent
import com.intellij.ui.components.JBPanel
import java.awt.*
import javax.swing.JComponent

abstract class AbstractGridBagPanel<T : AbstractGridBagPanel<T>> : JBPanel<T>() {
    init {
        layout = GridBagLayout()
    }

    protected fun addElement(
        element: Component,
        gridx: Int = 0,
        gridy: Int = 0,
        weightx: Double = 1.0,
        weighty: Double = 0.0,
        anchor: Int = GridBagConstraints.NORTH
    ) {
        val constraints = GridBagConstraints()
        constraints.fill = GridBagConstraints.HORIZONTAL
        constraints.gridx = gridx
        constraints.gridy = gridy
        constraints.weightx = weightx
        constraints.weighty = weighty
        constraints.insets = DEFAULT_INSETS
        constraints.anchor = anchor
        add(element, constraints)
    }

    protected fun createLabeled(
        component: JComponent,
        labelText: String,
        labelLocation: String = BorderLayout.WEST
    ): LabeledComponent<JComponent> {
        val labeled = LabeledComponent.create(component, labelText)
        labeled.labelLocation = labelLocation
        return labeled
    }

    companion object {
        const val DEFAULT_TEXT_FIELD_SIZE = 20
        val DEFAULT_INSETS = Insets(5, 5, 5, 5)
    }
}