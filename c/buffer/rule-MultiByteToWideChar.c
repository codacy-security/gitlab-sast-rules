// License: MIT (c) GitLab Inc.
#include<stdio.h>

int main() {
  printf("Hello HUMANS!");
}

void demo2() {
  char d[20];
  char s[20];
  int n;

  // ruleid: c_buffer_rule-MultiByteToWideChar
  MultiByteToWideChar(CP_ACP, 0, szName, -1, wszUserName, sizeof(wszUserName));

  // ruleid: c_buffer_rule-MultiByteToWideChar
  MultiByteToWideChar(CP_ACP, 0, szName, -1, wszUserName, sizeof wszUserName);

  // ruleid: c_buffer_rule-MultiByteToWideChar
  MultiByteToWideChar(CP_ACP, 0, szName, -1, wszUserName, sizeof(wszUserName) / sizeof(wszUserName[0]));

  // ruleid: c_buffer_rule-MultiByteToWideChar
  MultiByteToWideChar(CP_ACP, 0, szName, -1, wszUserName, sizeof wszUserName / sizeof(wszUserName[0]));
}
