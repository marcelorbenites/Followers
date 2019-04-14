package com.marcerlorbenites.followers

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    FollowerListComponentTest::class,
    FollowerDetailComponentTest::class
)
class ComponentTestSuite
