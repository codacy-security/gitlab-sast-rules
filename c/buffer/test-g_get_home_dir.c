// License: MIT (c) GitLab Inc.

#include <glib.h>

#include <glib/gprintf.h>

int main() {
  const gchar *
    gchar = g_get_home_dir(void);
  printf(gchar);
  return 0;
}