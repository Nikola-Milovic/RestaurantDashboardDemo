package com.nikolam.menu.ui.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.nikolam.menu.R
import com.nikolam.menu.di.testDataModule
import com.nikolam.menu.di.testViewModelModule
import com.nikolam.menu.utils.launchFragmentScenario
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules


@RunWith(AndroidJUnit4ClassRunner::class)
@MediumTest
class MenuItemsDisplayTest {

    val navController = TestNavHostController(
        ApplicationProvider.getApplicationContext())


    private fun launchMyFragmentScenario(bundle: Bundle?): FragmentScenario<MenuFragment>
    //viewModel factory can be easily injected if you use FragmentFactory
            = launchFragmentScenario(bundle, MenuFragment(), navController)


    class MenuScreen : Screen<MenuScreen>() {
        val menuRecyclerView =
            KRecyclerView(builder = { withId(R.id.menu_recycleView) }, itemTypeBuilder = {
                itemType(::Item)
            })
    }

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val name: KTextView = KTextView(parent) { withId((R.id.name_textView)) }
    }

    private val screen = MenuScreen()


    @Before
    fun setUp() {
        navController.setGraph(R.navigation.menu_nav_graph)

        loadKoinModules(listOf(testViewModelModule, testDataModule))
        launchMyFragmentScenario(null)
      //  launchFragmentInContainer<MenuFragment>()
    }

    @After
    fun tearDown() {
        unloadKoinModules(listOf(testViewModelModule, testDataModule))
    }

    @Test
    fun shouldChangeAddButtonEnableAfterChangingNoteText() {
        screen {
            menuRecyclerView {
                hasSize(5)
            }
        }
    }
}

