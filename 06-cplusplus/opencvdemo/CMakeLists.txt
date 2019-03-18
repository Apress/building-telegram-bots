cmake_minimum_required(VERSION 2.8.4)
project(opencvdemo)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -Wall")
set(Boost_USE_MULTITHREADED ON)

include_directories($HOME/projects/opencv/build/include )
include_directories(/usr/local/include ${OPENSSL_INCLUDE_DIR} ${Boost_INCLUDE_DIR})

if (CURL_FOUND)
    include_directories(${CURL_INCLUDE_DIRS})
    add_definitions(-DHAVE_CURL)
endif()

find_package(Threads REQUIRED)
find_package(OpenSSL REQUIRED)
find_package(Boost COMPONENTS system REQUIRED)
find_package(CURL)
find_package(OpenCV)

add_executable(opencvdemo src/main.cpp)

target_link_libraries(opencvdemo /usr/local/lib64/libopencv_core.so /usr/local/lib64/libopencv_imgcodecs.so /usr/local/lib64/libopencv_highgui.so /usr/local/lib/libTgBot.a ${CMAKE_THREAD_LIBS_INIT} ${OPENSSL_LIBRARIES} ${Boost_LIBRARIES} ${CURL_LIBRARIES})
