# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/django_sql_injection_raw.py
# hash:  8eee173

from django.db.models.expressions import RawSQL
from django.contrib.auth.models import User

User.objects.annotate(val=RawSQL('secure', []))
User.objects.annotate(val=RawSQL('%secure' % 'nos', []))
User.objects.annotate(val=RawSQL('{}secure'.format('no'), []))
raw = '"username") AS "val" FROM "auth_user" WHERE "username"="admin" --'
User.objects.annotate(val=RawSQL(raw, []))
raw = '"username") AS "val" FROM "auth_user"' \
      ' WHERE "username"="admin" OR 1=%s --'
User.objects.annotate(val=RawSQL(raw, [0]))
